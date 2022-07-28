package org.mili;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class MyReduce extends Reducer<TempWritable, IntWritable, Text,IntWritable> {
    @Override
    protected void reduce(TempWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> iterator = values.iterator();
        context.write(new Text(key.toString()), iterator.next());
        String cursor = key.toString();
        while (iterator.hasNext() ) {
            IntWritable next = iterator.next();
            if (!cursor.equals(key.toString())) {
                context.write(new Text(key.toString()), next);
                return;
            }
            cursor = key.toString();
        }
    }
}

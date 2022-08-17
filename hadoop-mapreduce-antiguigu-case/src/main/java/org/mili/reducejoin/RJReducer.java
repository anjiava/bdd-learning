package org.mili.reducejoin;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class RJReducer extends Reducer<RJBean, NullWritable,RJBean,NullWritable> {

    @Override
    protected void reduce(RJBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<NullWritable> iterator = values.iterator();
        iterator.next();
        String pName = key.getpName();
        while (iterator.hasNext()) {
            NullWritable next = iterator.next();
            key.setpName(pName);
            context.write(key, NullWritable.get());
        }

    }
}

package org.mili;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;

public class MyMap extends Mapper<LongWritable, Text,TempWritable, IntWritable> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    TempWritable mapKey = new TempWritable();
    IntWritable mapOut = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] split = StringUtils.split(value.toString(), '\t');
        if (split.length != 2) {

            return;
        }
        TemporalAccessor parse = formatter.parse(split[0]);
        mapKey.setYear(parse.get(ChronoField.YEAR));
        mapKey.setMonth(parse.get(ChronoField.MONTH_OF_YEAR));
        mapKey.setDay(parse.get(ChronoField.DAY_OF_MONTH));
        String temp = split[1];
        int tempNum = Integer.parseInt(temp.substring(0, temp.lastIndexOf("c")));
        mapKey.setTemp(tempNum);
        mapOut.set(tempNum);
        context.write(mapKey,mapOut);
    }
}

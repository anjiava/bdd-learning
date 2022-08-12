package org.mili.writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/08/09
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    Text outKey = new Text();
    FlowBean outValue = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        outKey.set(split[1]);
        outValue.setUp(Long.valueOf(split[split.length-3]));
        outValue.setDown(Long.valueOf(split[split.length-2]));
        outValue.setTotal(0L);
        context.write(outKey, outValue);
    }
}

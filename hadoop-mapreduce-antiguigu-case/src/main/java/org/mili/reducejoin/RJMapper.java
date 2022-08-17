package org.mili.reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class RJMapper extends Mapper<LongWritable, Text, RJBean, NullWritable> {
    String fileName;
    RJBean bean = new RJBean();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
//  获取当前输入文件的名称
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        fileName = inputSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        if (fileName.contains("order")) {
            bean.setId(Integer.valueOf(split[0]));
            bean.setpId(Integer.valueOf(split[1]));
            bean.setAmount(Integer.valueOf(split[2]));
            bean.setpName("");
        }else {
            bean.setId(-1);
            bean.setpId(Integer.valueOf(split[0]));
            bean.setAmount(-1);
            bean.setpName(split[1]);
        }
        context.write(bean, NullWritable.get());
    }
}

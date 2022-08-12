package org.mili.writable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 根据输入数据，统计出每个手机的上行、下行流量以及总共的流量
 *
 * @author mamenglong
 * @date 2022/08/09
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());
        job.setJobName("count flow");
        job.setJarByClass(FlowDriver.class);

        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 这里可以使用Combiner减少网络传输数据量
        job.setCombinerClass(FlowCombiner.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\dev-tools\\hadoop-2.7.2\\data\\input"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\dev-tools\\hadoop-2.7.2\\data\\output\\writable"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}

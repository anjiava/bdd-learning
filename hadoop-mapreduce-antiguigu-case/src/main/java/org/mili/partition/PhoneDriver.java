package org.mili.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.mili.writable.*;

import java.io.IOException;

/**
 * 在 {@link org.mili.writable.FlowDriver}的基础上实现对统计结果的分区处理，
 * 即将不同前缀的手机号的统计结果放到不同的输出结果文件中。
 * @author mamenglong
 * @date 2022/08/09
 */
public class PhoneDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());
        job.setJobName("count flow");
        job.setJarByClass(FlowDriver.class);
        job.setNumReduceTasks(5);
        job.setPartitionerClass(PhonePartition.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // 这里可以使用Combiner减少网络传输数据量
        // job.setCombinerClass(FlowCombiner.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\dev-tools\\hadoop-2.7.2\\data\\input"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\dev-tools\\hadoop-2.7.2\\data\\output\\partitioner"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}

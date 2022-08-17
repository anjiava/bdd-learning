package org.mili.reducejoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import sun.tools.java.ClassPath;

import java.io.IOException;

public class RJDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());

        job.setJobName("reduce join");
        job.setUser("root");
        job.setJarByClass(RJDriver.class);
        job.setMapperClass(RJMapper.class);
        job.setReducerClass(RJReducer.class);
        job.setGroupingComparatorClass(RJCompartor.class);
        job.setMapOutputKeyClass(RJBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(RJBean.class);
        job.setOutputValueClass(NullWritable.class);
//        job.setGroupingComparatorClass();

        String input = Thread.currentThread().getContextClassLoader().getResource("input/reducejoin").getPath();
        String output = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output + "/output"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);


    }
}

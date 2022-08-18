package org.mili.mapjoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.mili.reducejoin.RJBean;
import org.mili.reducejoin.RJCompartor;
import org.mili.reducejoin.RJMapper;
import org.mili.reducejoin.RJReducer;

import java.io.IOException;
import java.net.URI;

public class MJDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = Job.getInstance(new Configuration());

        job.setJobName("map join");
        job.setUser("root");
        job.setJarByClass(MJDriver.class);

        job.setMapperClass(MJMapper.class);
        job.setMapOutputKeyClass(RJBean.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        String input = Thread.currentThread().getContextClassLoader().getResource("input/reducejoin").getPath();
        job.addCacheFile(URI.create(input+"/prod"));
        String output = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        FileInputFormat.setInputPaths(job, new Path(input+"/order"));
        FileOutputFormat.setOutputPath(job, new Path(output + "/output"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);


    }
}

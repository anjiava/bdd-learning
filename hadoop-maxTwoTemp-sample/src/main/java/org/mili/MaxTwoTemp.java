package org.mili;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 *
 */
public class MaxTwoTemp {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        if (args.length != 2) {
            System.err.println("Usage: WordCount <input path> <output path>");
        }
        // Create a new Job
        Configuration configuration = new Configuration();
        Job job = Job.getInstance();
        job.setJarByClass(MaxTwoTemp.class);
        // Specify various job-specific parameters
        job.setJobName("max two temp");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        Path output = new Path(args[1]);
        FileSystem fileSystem = output.getFileSystem(configuration);
        if (fileSystem.exists(output)) {
            fileSystem.delete(output, true);
        }
        FileOutputFormat.setOutputPath(job, output);

        job.setMapperClass(MyMap.class);

        job.setReducerClass(MyReduce.class);
        job.setMapOutputKeyClass(TempWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setSortComparatorClass(MySortComparator.class);
        job.setGroupingComparatorClass(MyGroupComparator.class);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
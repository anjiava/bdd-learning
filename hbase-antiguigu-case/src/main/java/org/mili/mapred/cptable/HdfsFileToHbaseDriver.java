package org.mili.mapred.cptable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * 将hdfs中的文件数据导入到hbase的表中
 *
 * @author mamenglong
 * @date 2022/09/06
 */
public class HdfsFileToHbaseDriver {

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Tool() {
            @Override
            public int run(String[] strings) throws Exception {
                Job job = Job.getInstance();
                job.setJarByClass(HdfsFileToHbaseDriver.class);
                job.setJobName("put hdfs file to hbase table");
                TableMapReduceUtil.initTableMapperJob("antiguigu:student", new Scan(), HdfsFileToHbaseDriver.MyMapper.class,ImmutableBytesWritable.class,Put.class,job);

                job.setMapperClass(MyMapper.class);
                job.setMapOutputKeyClass(ImmutableBytesWritable.class);
                job.setMapOutputValueClass(Text.class);

                TableMapReduceUtil.initTableReducerJob("antiguigu:student_copy", MyReducer.class, job);
                return job.waitForCompletion(true) ? JobStatus.State.SUCCEEDED.getValue() : JobStatus.State.FAILED.getValue();
            }

            @Override
            public void setConf(Configuration configuration) {

            }

            @Override
            public Configuration getConf() {
                return null;
            }
        }, args);
    }

    public static class MyMapper extends TableMapper<ImmutableBytesWritable, Put> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split(",");
            byte[] rowKey = Bytes.toBytes(split[0]);
            String cellValue = split[1];
            Put put = new Put(rowKey);
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes(cellValue));
            context.write(new ImmutableBytesWritable(rowKey), put);
        }

    }

    public static class MyReducer extends TableReducer<ImmutableBytesWritable, Put, NullWritable> {
        @Override
        protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put put : values) {
                context.write(NullWritable.get(), put);
            }
        }
    }

}


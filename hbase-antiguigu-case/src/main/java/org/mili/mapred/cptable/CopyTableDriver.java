package org.mili.mapred.cptable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobStatus;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/09/06
 */
public class CopyTableDriver {

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new Tool() {
            @Override
            public int run(String[] strings) throws Exception {
                Job job = Job.getInstance();
                job.setJarByClass(CopyTableDriver.class);
                job.setJobName("hbase copy table to ");
                TableMapReduceUtil.initTableMapperJob("antiguigu:student", new Scan(), MyMapper.class,ImmutableBytesWritable.class,Put.class,job);
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
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {
            for (Cell cell : value.rawCells()) {
                Put put = new Put(key.get());
                put.addColumn(CellUtil.cloneFamily(cell), CellUtil.cloneQualifier(cell), CellUtil.cloneValue(cell));
                context.write(key,put);
            }
        }
    }

    public static class MyReducer extends TableReducer<ImmutableBytesWritable, Put, NullWritable> {
        @Override
        protected void reduce(ImmutableBytesWritable key, Iterable<Put> values, Context context) throws IOException, InterruptedException {
            for (Put put : values) {
                context.write(NullWritable.get(),put);
            }
        }
    }

}


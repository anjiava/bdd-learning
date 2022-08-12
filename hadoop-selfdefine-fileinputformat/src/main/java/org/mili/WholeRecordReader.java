package org.mili;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/08/08
 */
public class WholeRecordReader extends RecordReader<Text, BytesWritable> {
    FileSplit split;
    FSDataInputStream inputStream;
    Text key;
    BytesWritable value;

    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        split = (FileSplit) inputSplit;
        FileSystem fileSystem = split.getPath().getFileSystem(taskAttemptContext.getConfiguration());
        inputStream = fileSystem.open(split.getPath());
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (null == key) {
            key = new Text(split.getPath().getName());
            byte[] buff = new byte[(int) split.getLength()];
            inputStream.read(buff);
            value = new BytesWritable(buff);
            return true;
        }
        return false;
    }

    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return null == key ? 0 : 1;
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeStream(inputStream);
    }
}

package org.mili.mapjoin;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mamenglong
 * @date 2022/08/18
 */
public class MJMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    Map<String, String> prodMap = new HashMap<>();
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI cacheFile = context.getCacheFiles()[0];
        String path = cacheFile.getPath();
        // 这里不能用hadoop的fsdatainputstream,读取中文的时候会出现乱码
        // FileSystem fileSystem = FileSystem.get(context.getConfiguration());
        // FSDataInputStream reader = fileSystem.open(new Path(path));
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while (null != (line = reader.readLine())) {
            String[] split = line.split("\t");
            prodMap.put(split[0],split[1]);
        }
        IOUtils.closeStream(reader);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        context.write(new Text(split[0] + "\t" + prodMap.getOrDefault(split[1],"null") + "\t" + split[2]), NullWritable.get());
    }
}

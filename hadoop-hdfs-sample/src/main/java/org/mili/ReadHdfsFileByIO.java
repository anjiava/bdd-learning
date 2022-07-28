package org.mili;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 根据java的输入输出流直接读取hadoop上的文件
 */
public class ReadHdfsFileByIO {
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) throws IOException {
        URL url = new URL("hdfs://node001:8020/user/root/maxtemp/output4/part-r-00000");
        InputStream inputStream = url.openStream();
        IOUtils.copyBytes(inputStream,System.out,4096,true);

    }
}
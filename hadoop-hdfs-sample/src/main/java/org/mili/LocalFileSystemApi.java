package org.mili;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocalFileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * 本地文件系统对象
 *
 * 如果类路径下没有对应的配置，则通过 FileSystem.get获取到的文件系统对象为LocalFileSystem
 */
public class LocalFileSystemApi {
    public static void main(String[] args) throws IOException {
//        LocalFileSystem fileSystem = FileSystem.getLocal(new Configuration());
        FileSystem fileSystem = FileSystem.get(new Configuration());

        FSDataInputStream inputStream = fileSystem.open(new Path("D:\\code\\bdd-learning\\pom.xml"));
        IOUtils.copyBytes(inputStream,System.out,4096,false);
        inputStream.seek(0);
        System.out.println("--------------");
        IOUtils.copyBytes(inputStream,System.out,4096,true);

    }
}

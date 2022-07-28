package org.mili;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.net.URI;

public class FileSystemApi {
    public static void main(String[] args) {

    }

    public void copyLocalToRemote() throws IOException {
        FileSystem fs = FileSystem.get(URI.create("hdfs://node001:8020"), new Configuration());

    }
}

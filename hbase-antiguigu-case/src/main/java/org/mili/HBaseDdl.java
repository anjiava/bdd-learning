package org.mili;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/09/06
 */
public class HBaseDdl {
    HBaseAdmin admin;

    public HBaseDdl() throws IOException {
        Connection connection = ConnectionFactory.createConnection();
        admin = (HBaseAdmin) connection.getAdmin();
    }

    public void createNamespaceIfNotExist(String namespace) throws IOException {
        NamespaceDescriptor namespaceDescriptor = null;
        try {
            namespaceDescriptor = admin.getNamespaceDescriptor(namespace);
        } catch (NamespaceNotFoundException e) {
            admin.createNamespace(NamespaceDescriptor.create(namespace).build());
            namespaceDescriptor = admin.getNamespaceDescriptor(namespace);
        }
        System.out.println(namespaceDescriptor.getName());
    }

    public void createTableIfNotExist(String tableName,String namespace,String... families) throws IOException {
        TableName t = StringUtils.isNotBlank(namespace) ? TableName.valueOf(namespace, tableName) : TableName.valueOf(tableName);

        if (!admin.tableExists(t)) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(t);
            for (String family : families) {
                HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
                tableDescriptor.addFamily(columnDescriptor);
            }
            admin.createTable(tableDescriptor);
        }
    }

    public static void main(String[] args) throws IOException {
        HBaseDdl hBaseDdl = new HBaseDdl();
        hBaseDdl.createTableIfNotExist("teacher","antiguigu","info","clazz");
    }
}

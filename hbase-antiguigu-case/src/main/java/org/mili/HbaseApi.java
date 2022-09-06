package org.mili;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/09/05
 */
public class HbaseApi {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        // config.set("hbase.zookeeper.quorum","node002:2181,node003:2181,node004:2181");
        Connection connection = ConnectionFactory.createConnection(config);
        HBaseAdmin admin = (HBaseAdmin)connection.getAdmin();


        NamespaceDescriptor namespaceDescriptor = null;
        try {
            namespaceDescriptor = admin.getNamespaceDescriptor("antiguigu");
        } catch (NamespaceNotFoundException e) {
            admin.createNamespace(NamespaceDescriptor.create("antiguigu").build());
            namespaceDescriptor = admin.getNamespaceDescriptor("antiguigu");
        }
        System.out.println(namespaceDescriptor.toString());


        TableName table = TableName.valueOf("antiguigu:student");
        if (!admin.tableExists(table)) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(table);
            HColumnDescriptor columnFamily = new HColumnDescriptor("info");
            tableDescriptor.addFamily(columnFamily);
            admin.createTable(tableDescriptor);
        }
        Table tableHandler = connection.getTable(table);
        System.out.println(tableHandler.toString());

        Get get = new Get(Bytes.toBytes("1001"));
        Result result = tableHandler.get(get);
        if (result.isEmpty()) {
            System.out.println("result is null");
        } else {
            CellScanner cellScanner = result.cellScanner();
            while (cellScanner.advance()) {
                // System.out.println(cellScanner.current().toString());
                System.out.println(Bytes.toString(CellUtil.cloneValue(cellScanner.current())));
            }
        }

        // Get get1 = new Get(Bytes.toBytes("1002"));
        // Result result1 = tableHandler.get(get);
        // if (result1.isEmpty()) {
        //     System.out.println("result is null");
        //     Put
        //     tableHandler.put();
        // } else {
        //     CellScanner cellScanner = result.cellScanner();
        //     while (cellScanner.advance()) {
        //         System.out.println(cellScanner.current().toString());
        //     }
        // }



    }
}

package org.mili;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * HBase Data manipulation language
 * @author mamenglong
 * @date 2022/09/05
 */
public class HBaseDml {
    private Connection connection;

    public HBaseDml() {
        this.getConfigInstance();
    }


    private void getConfigInstance() {
        Configuration config = HBaseConfiguration.create();
        try {
             connection = ConnectionFactory.createConnection(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void get(String t,String rk,String family,String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(t));
        Get get = new Get(Bytes.toBytes(rk));
        if (StringUtils.isNotBlank(family) && StringUtils.isNotBlank(col)) {
            get.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
        }
        Result result = table.get(get);
        if (result.isEmpty()) {
            System.out.println("result is empty");
        } else {
            for (Cell cell : result.rawCells()) {
                System.out.println("family:"+Bytes.toString(CellUtil.cloneFamily(cell))+",qualifier:"+Bytes.toString(CellUtil.cloneQualifier(cell))+
                        ",row:"+Bytes.toString(CellUtil.cloneRow(cell))+",value:"+Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    public void put(String t, String rk,String family,String col, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(t));
        Put put = new Put(Bytes.toBytes(rk));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(col), Bytes.toBytes(value));
        table.put(put);

    }

    public void delete(String t, String rk,String family,String col) throws IOException {
        Table table = connection.getTable(TableName.valueOf(t));
        Delete delete = new Delete(Bytes.toBytes(rk));
        if (StringUtils.isNotBlank(family) && StringUtils.isNotBlank(col)) {
            delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(col));
        }
        table.delete(delete);
    }

    public static void main(String[] args) throws IOException {
        HBaseDml hBaseDml = new HBaseDml();
        // hBaseDml.get("antiguigu:student","1001","info","name");
        // hBaseDml.put("antiguigu:student","1001","info","age","18");
        hBaseDml.get("antiguigu:student","1001",null,null);
        hBaseDml.delete("antiguigu:student","1001","info","age");
        // hBaseDml.delete("antiguigu:student","1001","sex","1");
        hBaseDml.get("antiguigu:student","1001",null,null);
    }


}

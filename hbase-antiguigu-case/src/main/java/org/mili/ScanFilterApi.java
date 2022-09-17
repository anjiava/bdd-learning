package org.mili;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * 过滤器查询
 * 过滤器不通过索引去快速查询，因此效率会比较低。但是可以通过一定的方式去优化
 * @author mamenglong
 * @date 2022/09/17
 */
public class ScanFilterApi {

    private Connection connection;

    public ScanFilterApi() {
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

    private void printCell(ResultScanner scanner) {
        for (Result result : scanner) {
            for (Cell cell : result.rawCells()) {
                System.out.println("family:" + Bytes.toString(CellUtil.cloneFamily(cell)) + ",qualifier:" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                        ",row:" + Bytes.toString(CellUtil.cloneRow(cell)) + ",value:" + Bytes.toString(CellUtil.cloneValue(cell)));
            }
        }
    }

    /**
     * 查询出 rowkey >= 1000的记录
     *
     * @param t
     * @throws IOException
     */
    public void scanRowKeyGe(String t) throws IOException {
        scanFilter(t, () -> {
            Scan scan = new Scan();
            BinaryComparator comparator = new BinaryComparator(Bytes.toBytes("1000"));
            RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, comparator);
            scan.setFilter(rowFilter);
            return scan;
        });

    }

    /**
     * 查询出 由三个数字组成的rowkey的记录
     *
     * @param t
     */
    public void scanRowKeyHaveThreeDigital(String t) throws IOException {
        scanFilter(t, () -> {
            Scan scan = new Scan();
            RegexStringComparator comparator = new RegexStringComparator("^\\d{3}$");
            RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, comparator);
            scan.setFilter(rowFilter);
            return scan;
        });


    }

    public void scanFilter(String t, Supplier<Scan> supplier) throws IOException {
        Table table = connection.getTable(TableName.valueOf(t));
        printCell(table.getScanner(supplier.get()));
    }
}

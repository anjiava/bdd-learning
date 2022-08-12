package org.mili.partition;

import org.apache.hadoop.io.ElasticByteBufferPool;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
import org.mili.writable.FlowBean;

/**
 * @author mamenglong
 * @date 2022/08/09
 */
public class PhonePartition extends Partitioner<Text, FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int i) {
        switch (text.toString().substring(0, 3)) {
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}

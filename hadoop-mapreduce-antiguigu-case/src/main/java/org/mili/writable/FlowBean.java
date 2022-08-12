package org.mili.writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author mamenglong
 * @date 2022/08/09
 */
public class FlowBean implements Writable {
    /**
     * 上行流量
     */
    private Long up;
    /**
     * 下行流量
     */
    private Long down;
    /**
     * 总流量
     */
    private Long total;
    public FlowBean() {

    }


    public FlowBean(Long up, Long down, Long total) {
        this.up = up;
        this.down = down;
        this.total = total;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(up);
        dataOutput.writeLong(down);
        dataOutput.writeLong(total);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        up = dataInput.readLong();
        down = dataInput.readLong();
        total = dataInput.readLong();
    }

    @Override
    public String toString() {
        return "" + up + "\t" + down + "\t" + total;
    }

    public Long getUp() {
        return up;
    }

    public void setUp(Long up) {
        this.up = up;
    }

    public Long getDown() {
        return down;
    }

    public void setDown(Long down) {
        this.down = down;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

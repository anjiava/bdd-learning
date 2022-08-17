package org.mili.reducejoin;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class RJBean implements WritableComparable<RJBean> {
    private Integer id;
    private Integer pId;
    private Integer amount;
    private String pName;

    @Override
    public int compareTo(RJBean o) {
        int compare = pId.compareTo(o.getpId());
        if (0 == compare) {
            return o.pName.compareTo(pName);
        }
        return compare;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(id);
        out.writeInt(pId);
        out.writeInt(amount);
        out.writeUTF(pName);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        id = in.readInt();
        pId = in.readInt();
        amount = in.readInt();
        pName = in.readUTF();
    }

    @Override
    public String toString() {
        return  id + "\t" + amount + "\t" + pName ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }


}

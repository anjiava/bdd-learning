package org.mili.reducejoin;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class RJCompartor extends WritableComparator {
    public RJCompartor() {
        super(RJBean.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        RJBean o1 = (RJBean) a;
        RJBean o2 = (RJBean) b;

        return o1.getpId().compareTo(o2.getpId());
    }
}

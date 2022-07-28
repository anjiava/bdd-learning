package org.mili;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupComparator extends WritableComparator {
    public MyGroupComparator() {
        super(TempWritable.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TempWritable var1 = (TempWritable) a;
        TempWritable var2 = (TempWritable) b;
        int c1 = Integer.compare(var1.getYear(), var2.getYear());
        if (c1 == 0) {
            return Integer.compare(var1.getMonth(), var2.getMonth());
        }
        return c1;
    }
}

package org.mili;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 *
 */
public class MySortComparator extends WritableComparator {
    public MySortComparator() {
        super(TempWritable.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TempWritable var1 = (TempWritable) a;
        TempWritable var2 = (TempWritable) b;
        int c1 = Integer.compare(var1.getYear(), var2.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(var1.getMonth(), var2.getMonth());
            if (c2 == 0) {
                return -Integer.compare(var1.getTemp(), var2.getTemp());
            }
            return c2;
        }
        return c1;
    }
}

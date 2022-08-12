package org.mili.writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author mamenglong
 * @date 2022/08/09
 */
public class FlowCombiner extends Reducer<Text,FlowBean, Text,FlowBean> {
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        Iterator<FlowBean> iterator = values.iterator();
        long total,up = 0,down = 0;
        while (iterator.hasNext()) {
            FlowBean next = iterator.next();
            up += next.getUp();
            down += next.getDown();
        }
        total = up + down;

        context.write(key, new FlowBean(up, down, total));
    }
}

package org.mili;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TempWritable implements WritableComparable<TempWritable> {
    private Integer year;

    private Integer month;
    private Integer day;
    private Integer temp;


    @Override
    public int compareTo(TempWritable var) {

        int c1 = Integer.compare(this.year, var.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(this.month, var.getMonth());
            if (c2 == 0) {
                return Integer.compare(this.day, var.getDay());
            }
            return c2;
        }
        return c1;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeInt(temp);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        temp = in.readInt();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }
}

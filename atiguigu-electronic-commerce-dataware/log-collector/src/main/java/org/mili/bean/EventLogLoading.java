package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class EventLogLoading implements Serializable {

    private String action;
    private String loadingTime;
    private String loadingWay;
    private String extend1;
    private String extend2;
    private String type;
    private String type1;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(String loadingTime) {
        this.loadingTime = loadingTime;
    }

    public String getLoadingWay() {
        return loadingWay;
    }

    public void setLoadingWay(String loadingWay) {
        this.loadingWay = loadingWay;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getExtend2() {
        return extend2;
    }

    public void setExtend2(String extend2) {
        this.extend2 = extend2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
}

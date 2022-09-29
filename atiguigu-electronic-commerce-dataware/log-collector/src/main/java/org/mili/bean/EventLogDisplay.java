package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class EventLogDisplay implements Serializable {
    private String action;
    private String newsid;
    private String place;
    private String extend1;
    private String category;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

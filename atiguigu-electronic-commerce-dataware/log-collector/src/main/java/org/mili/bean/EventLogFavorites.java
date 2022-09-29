package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class EventLogFavorites implements Serializable {
    private Integer id;
    private Integer courseId;
    private Integer userId;
    private String addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}

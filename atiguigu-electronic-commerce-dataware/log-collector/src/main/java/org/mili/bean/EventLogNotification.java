package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class EventLogNotification implements Serializable {
    private String action;
    private String type;
    private String apTime;
    private String content;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApTime() {
        return apTime;
    }

    public void setApTime(String apTime) {
        this.apTime = apTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

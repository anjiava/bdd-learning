package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class EventLogActiveBackground implements Serializable {
    private String activeSource;

    public String getActiveSource() {
        return activeSource;
    }

    public void setActiveSource(String activeSource) {
        this.activeSource = activeSource;
    }
}

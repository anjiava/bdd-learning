package org.mili.bean;

import java.io.Serializable;

/**
 * @author mamenglong
 * @date 2022/09/26
 */
public class AppErrorLog implements Serializable {

    private String errorBrief;

    private String errorDetail;

    public String getErrorBrief() {
        return errorBrief;
    }

    public void setErrorBrief(String errorBrief) {
        this.errorBrief = errorBrief;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}

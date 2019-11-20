package com.shura.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TPlan {
    private Integer planId;

    private String planName;

    private String status;

    private String isFeedback;

    private Date beginDate;

    public TPlan(Integer planId, String planName, String status, String isFeedback, Date beginDate, Date endDate, Integer taskId, String feedbackInfo, String planDesc) {
        this.planId = planId;
        this.planName = planName;
        this.status = status;
        this.isFeedback = isFeedback;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.taskId = taskId;
        this.feedbackInfo = feedbackInfo;
        this.planDesc = planDesc;
    }

    private Date endDate;

    private Integer taskId;

    private String feedbackInfo;

    private String planDesc;

    private SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(String isFeedback) {
        this.isFeedback = isFeedback == null ? null : isFeedback.trim();
    }

    public String  getBeginDate() {
        return format.format(beginDate);
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String  getEndDate() {

        return format.format(endDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getFeedbackInfo() {
        return feedbackInfo;
    }

    public void setFeedbackInfo(String feedbackInfo) {
        this.feedbackInfo = feedbackInfo == null ? null : feedbackInfo.trim();
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc == null ? null : planDesc.trim();
    }
}

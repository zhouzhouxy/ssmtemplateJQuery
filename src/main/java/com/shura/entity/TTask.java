package com.shura.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TTask {
    private Integer taskId;

    private String taskName;

    @Override
    public String toString() {
        return "TTask{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", realBeginDate=" + realBeginDate +
                ", realEndDate=" + realEndDate +
                ", status='" + status + '\'' +
                ", implementorId=" + implementorId +
                ", assignerId=" + assignerId +
                ", taskDesc='" + taskDesc + '\'' +
                '}';
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
    @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
    @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化
    private Date endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
    @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化
    private Date realBeginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
    @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化
    private Date realEndDate;

    private String status;

    private Integer implementorId;

    private Integer assignerId;

    private String taskDesc;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public TTask(Integer taskId, String taskName, Date beginDate, Date endDate, Date realBeginDate, Date realEndDate, String status, Integer implementorId, Integer assignerId, String taskDesc) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.realBeginDate = realBeginDate;
        this.realEndDate = realEndDate;
        this.status = status;
        this.implementorId = implementorId;
        this.assignerId = assignerId;
        this.taskDesc = taskDesc;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public String getBeginDate() {
      //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println("格式化后的时间为"+format.format(beginDate));
        return format.format(beginDate);
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(beginDate);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRealBeginDate() {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(realBeginDate==null){
            return null;
        }else{
            return format.format(realBeginDate);
        }
    }

    public void setRealBeginDate(Date realBeginDate) {
        this.realBeginDate = realBeginDate;
    }

    public String getRealEndDate() {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(realEndDate==null){
            return null;
        }else{
            return format.format(realEndDate);
        }
    }

    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getImplementorId() {
        return implementorId;
    }

    public void setImplementorId(Integer implementorId) {
        this.implementorId = implementorId;
    }

    public Integer getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Integer assignerId) {
        this.assignerId = assignerId;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc == null ? null : taskDesc.trim();
    }
}

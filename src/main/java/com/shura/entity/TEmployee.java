package com.shura.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TEmployee {
    private Integer employeeId;

    public TEmployee() {
    }

    /*@DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
        @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化*/
    private Date birthday;

    private String duty;

    /*@DateTimeFormat(pattern = "yyyy-MM-dd") //页面写入数据时格式化
    @JSONField(format = "yyyy-MM-dd")   //数据库导出页面时json格式化*/
    private Date enrolldate;

    public TEmployee(Integer employeeId, String employeeName, String password, String realName, String sex, Date birthday, String duty, Date enrolldate, String education, String major, String experience, Integer roleId, Integer parentId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.password = password;
        this.realName = realName;
        this.sex = sex;
        this.birthday = birthday;
        this.duty = duty;
        this.enrolldate = enrolldate;
        this.education = education;
        this.major = major;
        this.experience = experience;
        this.roleId = roleId;
        this.parentId = parentId;
    }

    private String employeeName;

    private String password;

    private String realName;

    private String sex;

    private String education;

    private String major;

    private String experience;

    private Integer roleId;

    private Integer parentId;

    private SimpleDateFormat format=new SimpleDateFormat();

    public TEmployee(Integer employeeId, Date birthday, String duty, Date enrolldate, String employeeName, String password, String realName, String sex, String education, String major, String experience, Integer roleId, Integer parentId) {
        this.employeeId = employeeId;
        this.birthday = birthday;
        this.duty = duty;
        this.enrolldate = enrolldate;
        this.employeeName = employeeName;
        this.password = password;
        this.realName = realName;
        this.sex = sex;
        this.education = education;
        this.major = major;
        this.experience = experience;
        this.roleId = roleId;
        this.parentId = parentId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        if(birthday!=null){
            return format.format(birthday);
        }
        return null;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getEnrolldate() {
        if(enrolldate!=null){
            return format.format(enrolldate);
        }
        return null;
    }

    public void setEnrolldate(Date enrolldate) {
        this.enrolldate = enrolldate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "TEmployee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", duty='" + duty + '\'' +
                ", enrolldate=" + enrolldate +
                ", education='" + education + '\'' +
                ", major='" + major + '\'' +
                ", experience='" + experience + '\'' +
                ", roleId=" + roleId +
                ", parentId=" + parentId +
                '}';
    }
}

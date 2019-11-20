package com.shura.service;

import com.github.pagehelper.PageInfo;
import com.shura.entity.TPlan;

import java.sql.Date;
import java.util.List;

public interface PlanService {
    List<TPlan> selectByTaskId(Integer taskId);

    TPlan selectByPlanId(Integer planId);

    //插入计划
    int insertPlan(TPlan plan);

    //删除计划
    int delPlan(Integer[] pids);

    //修改
    int updateByPlan(TPlan plan);

    //查询所有的计划
    PageInfo queryAllPlan(Integer pageNum,Integer pageSize);

    //根据条件查询
    PageInfo queryPlanByCondition(Integer pageNum, Integer pagesize, String planName, Integer taskId, Date beginDate1, Date beginDate2, Date endDate, Date endDate2, String feedBackInfo);
}

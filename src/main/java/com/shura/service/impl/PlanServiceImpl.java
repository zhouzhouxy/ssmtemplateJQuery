package com.shura.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shura.entity.TPlan;
import com.shura.entity.TPlanExample;
import com.shura.mapper.PlanMapper;
import com.shura.mapper.TPlanMapper;
import com.shura.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
//    @Autowired
    private TPlanMapper planMapper=null;

    public TPlanMapper getPlanMapper() {
        return planMapper;
    }

    public void setPlanMapper(TPlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public List<TPlan> selectByTaskId(Integer taskId) {
        TPlanExample tPlanExample = new TPlanExample();
        TPlanExample.Criteria criteria = tPlanExample.createCriteria();
        criteria.andTaskIdEqualTo(taskId);
        List<TPlan> tPlans = planMapper.selectByExample(tPlanExample);
//        List<TPlan> tPlans = plan.queryPlanByTaskId(taskId);
        return tPlans;

    }

    @Override
    public TPlan selectByPlanId(Integer planId) {
        return  planMapper.selectByPrimaryKey(planId);
    }

    @Override
    public int insertPlan(TPlan plan) {
        return planMapper.insert(plan);
    }

    @Override
    public int delPlan(Integer[] pids) {
        //删除
        TPlanExample example=new TPlanExample();
        TPlanExample.Criteria criteria = example.createCriteria();
        criteria.andPlanIdIn(Arrays.asList(pids));
        int i = planMapper.deleteByExample(example);
        if(i>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateByPlan(TPlan plan) {
//        int i = planMapper.updateByExample(plan, null);
        int i = planMapper.updateByPrimaryKey(plan);
        if(i>0){
            return 1;
        }
        return 0;
    }

    @Override
    public PageInfo queryAllPlan(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TPlan> tPlans = planMapper.selectByExample(null);
        PageInfo<TPlan> tPlanPageInfo = new PageInfo<>(tPlans);
        return tPlanPageInfo;
    }

    @Override
    public PageInfo queryPlanByCondition(Integer pageNum, Integer pageSize, String planName, Integer taskId, Date beginDate1, Date beginDate2, Date endDate, Date endDate2, String feedBackInfo) {
        PageHelper.startPage(pageNum, pageSize);

        TPlanExample example=new TPlanExample();
        TPlanExample.Criteria criteria = example.createCriteria();
        if(!planName.equals("")){
            criteria.andPlanNameLike(planName);
        }
        if(taskId>0){
            criteria.andTaskIdEqualTo(taskId);
        }
        if(beginDate1!=null && beginDate2!=null){
            criteria.andBeginDateBetween(beginDate1, beginDate2);
        }
        if(endDate!=null && endDate2!=null){
            criteria.andBeginDateBetween(endDate, endDate2);
        }
        if(!feedBackInfo.equals("")){
            criteria.andIsFeedbackEqualTo(feedBackInfo);
        }
        List<TPlan> tPlans = planMapper.selectByExample(example);
        PageInfo<TPlan> tPlanPageInfo = new PageInfo<>(tPlans);
        return tPlanPageInfo;
    }
}

package com.shura.controller;

import com.github.pagehelper.PageInfo;
import com.shura.entity.TPlan;
import com.shura.entity.TTask;
import com.shura.service.PlanService;
import com.shura.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.time.DateUtils.parseDate;

@RestController
@RequestMapping("plan")
public class PlanController {
    @Autowired
    @Resource
    private PlanService planService=null;

    @Autowired
    @Resource
    private TaskService taskService=null;
    //根据任务id查询所有的计划
    @RequestMapping("queryPlan")
    public ModelAndView queryPlanByTaskId(Integer taskId){
//        System.out.println("taskId="+taskId);
        ModelAndView mv = new ModelAndView();
        List<TPlan> tPlans = planService.selectByTaskId(taskId);
//        System.out.println(tPlans);
        mv.addObject("plans", tPlans);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    //根据planId查询详细计划
    @RequestMapping("planDetail")
    public Map planDetail(Integer planId){
        Map<Object, Object> map = new HashMap<>();
        //查询
        TPlan tPlan = planService.selectByPlanId(planId);
        //根据查询出来的计划信息中taskid查询任务名称
        TTask tTask = taskService.queryDescTask(tPlan.getTaskId());
        map.put("taskName", tTask.getTaskName());
        map.put("plan", tPlan);
        return map;
    }

    //提交新计划
    @RequestMapping("subPlan")
    public Map subPlan(Integer taskId, String planName, String planDesc, Date beginDate, Date endDate){

        Map<String, Object> map = new HashMap<>();
        //创建plan对象
        TPlan tPlan = new TPlan(null,planName,"未完成","未反馈", beginDate, endDate, taskId, null, planDesc);

        //插入
        int i = planService.insertPlan(tPlan);
        if(i>0){
            map.put("message","yes");
        }
        return map;
    }

    @RequestMapping("delPlan")
    public Map delPlan(HttpServletRequest request){
//        String[] ids = request.getParameterValues("ids");
        String ids = request.getParameter("ids");
        String[] split = ids.split(",");
        Integer[] pId=new Integer[split.length];
        //将ids转换为Interge类型的输入组
        for (int i=0;i<split.length;i++){
            pId[i]=new Integer(split[i]);
        }
        System.out.println(pId);
        Map<Object, Object> map = new HashMap<>();
        int i = planService.delPlan(pId);
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }

    @RequestMapping("feedBackInfo")
    public Map feedBackInfo(String status,String feedBackInfo,String info,Integer planId){
        //先查询再修改
        TPlan tPlan = planService.selectByPlanId(planId);
        tPlan.setStatus(status);
        tPlan.setIsFeedback(feedBackInfo);
        tPlan.setPlanDesc(info);

        //修改
        int i = planService.updateByPlan(tPlan);
        Map<Object, Object> map = new HashMap<>();
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }

    //查询所有的计划
    @RequestMapping("queryAllPlan")
    public Map queryAllPlan(Integer pageNum,Integer pageSize){
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = planService.queryAllPlan(pageNum, pageSize);
        map.put("pageInfo",pageInfo);
        return  map;
    }
    //根据条件查询计划
    @RequestMapping("queryPlanByCondition")
    public Map queryPlanByCondition(Integer pageSize,Integer pageNum,String planName,Integer taskId,String beginDate1,String beginDate2,String endDate,String endDate2,String feedBackInfo) throws ParseException {
        System.out.println(pageNum+";endDate"+endDate);
        //根据条件查询
//        DateFormatUtils.format(beginDate1,"yyyy-MM-dd ");
//                parseDate(beginDate1,  new String[] { "yyyy-MM-dd" });
//                parseDate(beginDate2,  new String[] { "yyyy-MM-dd" });
//                parseDate(endDate,  new String[] { "yyyy-MM-dd" });
//                parseDate(endDate,  new String[] { "yyyy-MM-dd" });
        //util.Date转sql.Date
//        java.sql.Date sqlDate = new java.sql.Date( parseDate(beginDate1,  new String[] { "yyyy-MM-dd" }));
        Date date=null,date2=null,date3=null,date4=null;
        if(!beginDate1.equals("")){
          date = Date.valueOf(beginDate1);
        }
        if(!beginDate2.equals("")){
              date2 = Date.valueOf(beginDate2);
        }
        if(!endDate.equals("")){
              date3 = Date.valueOf(endDate);
        }
        if(!endDate2.equals("")){
              date4 = Date.valueOf(endDate2);
        }
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = planService.queryPlanByCondition(pageNum, pageSize, planName, taskId,date,date2,date3,date4,feedBackInfo);
        map.put("pageInfo",pageInfo);
        return  map;
    }
}

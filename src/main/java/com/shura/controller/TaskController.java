package com.shura.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.shura.entity.TEmployee;
import com.shura.entity.TPlan;
import com.shura.entity.TTask;
import com.shura.service.EmployeeService;
import com.shura.service.PlanService;
import com.shura.service.TaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("taskController")
public class TaskController {

    @Autowired
    @Resource
    private EmployeeService employeeService=null;

    @Autowired
    @Resource
    private TaskService taskService=null;


    @Autowired
    @Resource
    private PlanService planService=null;
    //跳转页面
    @RequestMapping("setTask")
    public ModelAndView setTask(){
        ModelAndView mv = new ModelAndView();
//        System.out.println("跳转到设置任务页面");
        mv.setViewName("task/task1");
        return mv;
    }

    //跳转到调整任务页面
    @RequestMapping("adjustTask")
    public ModelAndView  adjustTask(){
        ModelAndView mv = new ModelAndView();
//        System.out.println("跳转到设置任务页面");
        mv.setViewName("adjustTask/adjustTask");
        return mv;
    }

    //跳转到跟踪任务页面
    @RequestMapping("trackerTask")
    public ModelAndView trakcerTask(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("trackTask/trackerTask");
        return mv;
    }

    //跳转到跟踪任务页面
    @RequestMapping("taskManager")
    public ModelAndView taskManager(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("taskManager/taskManager");
        return mv;
    }

    //跳转到查询计划的页面
    @RequestMapping("queryPlan")
    public ModelAndView queryPlan(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("queryEmployee/queryEmployee");
        return mv;
    }
    //跳转到查看人员的页面
    @RequestMapping("viewPerson")
    public ModelAndView viewPerson(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("viewPerson/person");
        return mv;
    }

    //跳转大用户管理界面
    @RequestMapping("userManager")
    public ModelAndView userManager(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userManager");
        return mv;
    }
    //跳转到员工管理页面
    @RequestMapping("employeeManager")
    public ModelAndView employeeManager(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/employeeManager");
        return mv;
    }
    //查询下属员工
    @RequestMapping("queryEmp")
    public Map queryEmployee(HttpServletRequest request){
        //通过获取存在session中的id查询下属
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
        Map<String, Object> map = new HashMap<>();
        //查询所有下属
        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(tEmployee);
        //将java对象转成json格式
        String s = JSON.toJSONString(tEmployee);
       // String str = json.toString();//将json对象转换为字符串
//        System.out.println(s);
        map.put("employee",(Object) tEmployees);
        return map;
    }


    //新建任务
    @RequestMapping("subTask")
    public Map insertTask(String taskName, String taskDesc, Date beignDate,Date endDate,Integer implementorId,String status,HttpServletRequest request){
//    public Map insertTask(@RequestBody String str){
        System.out.println(taskName+"====="+beignDate+"====="+implementorId);
        TEmployee employee = (TEmployee)request.getSession().getAttribute("employee");
        //将数据插入
        TTask tTask = new TTask(null,taskName,beignDate, endDate, null, null, status,implementorId,employee.getEmployeeId(),taskDesc);
        int insert = taskService.insert(tTask);
        System.out.println(insert);
        Map<String, String> map = new HashMap<>();
        map.put("message", "yes");
        return map;
    }

    //查看任务
    @RequestMapping("viewTask")
    public ModelAndView viewTask(){
        ModelAndView mv = new ModelAndView();
//        System.out.println("跳转到设置任务页面");
        mv.setViewName("task/viewTask");
        return mv;
    }

    //查询表格数据
    @RequestMapping("initTableData")
    public ModelAndView initTableData(Integer pageNum,Integer pageSize,String order,HttpServletRequest request){
//        Map<String, java.lang.Object> map = new HashMap<>();
        ModelAndView mv = new ModelAndView();
//        System.out.println(pageNum+""+pageSize+""+order);
        PageInfo pageInfo = taskService.queryTask(pageSize, pageNum,order);
        mv.addObject("pageInfo",pageInfo);
//        System.out.println(pageInfo);
        //查询属下员工
        //通过获取存在session中的id查询下属
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
        //查询所有下属
        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(tEmployee);
        mv.addObject("employee",tEmployees);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

    //查询任务详情信息
    @RequestMapping("queryDescTask")
    public Map queryDescTask(Integer taskId){
        Map<String, Object> map=new HashMap<>();
        //根据taskId查询任务详情
        TTask tTask = taskService.queryDescTask(taskId);
        map.put("taskDesc", tTask);
        //根据tTask.getImplementorId()查询员工
        TEmployee tEmployee = employeeService.queryEmployeeById(tTask.getImplementorId());

        map.put("employee", tEmployee);
        return map;
    }

    //调整任务加载表格数据
    @RequestMapping("getAdjustTaskInfo")
    public Map getTaskInfo(Integer pageNum,Integer pageSize,String keyWord,HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        //查询任务状态为未实施的任务
        String status="未实施";
        PageInfo pageInfo = taskService.selectUnimplmentorTask(pageNum, pageSize, keyWord, status);
        map.put("pageInfo", pageInfo);
        //查询属下员工
        //通过获取存在session中的id查询下属
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
        //查询所有下属
        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(tEmployee);

        map.put("employees",tEmployees);
        return map;
    }

    //删除任务
    @RequestMapping("delTask")
    public Map delTask(HttpServletRequest request){
//        String[] ids = request.getParameterValues("ids");
        String ids = request.getParameter("ids");
        String[] split = ids.split(",");
        Integer[] taskIds=new Integer[split.length];
        for(int i=0;i<split.length;i++){
            taskIds[i]=new Integer(split[i]);
        }
        HashMap<String, Object> map = new HashMap<>();
        //根据任务id删除未实施的任务
        int i = taskService.deleteByTaskIds(taskIds);
        if(i>0){
            map.put("message", "yes");
        }
        map.put("test", ids);
        return map;
    }

    //修改任务
    @RequestMapping("updateTask")
    public Map updateTask(Integer taskId,String taskName,String taskDesc,Date beginDate,Date endDate,Integer implementor_id){
        Map<String, String> map = new HashMap<>();
        //先查询task
        TTask tTask = taskService.queryDescTask(taskId);
        tTask.setTaskName(taskName);
        tTask.setTaskDesc(taskDesc);
        tTask.setBeginDate(beginDate);
        tTask.setEndDate(endDate);
        tTask.setImplementorId(implementor_id);
        int i = taskService.updateByTask(tTask);
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }

    //跟踪任务 列出所有状态为实施中的任务
    @RequestMapping("trackTask")
    public Map trackTask(Integer pageNum,Integer pageSize,String keyWord,HttpServletRequest request){
        Map<Object, Object> map = new HashMap<>();
        //查询任务状态为未实施的任务
        String status="实施中";
        PageInfo pageInfo = taskService.selectUnimplmentorTask(pageNum, pageSize, keyWord, status);
        map.put("pageInfo", pageInfo);
        //查询属下员工
        //通过获取存在session中的id查询下属
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
        //查询所有下属
        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(tEmployee);

        map.put("employees",tEmployees);
        return map;
    }

    //根据任务id查询详细任务信息和查询计划
    @RequestMapping("trackTaskAndPlan")
    public Map trackTaskAndPlan(Integer taskId){
        Map<String, Object> map = new HashMap<>();
        //查询详情的任务名称
        TTask tTask = taskService.queryDescTask(taskId);
        //根据任务ID查询所有的计划
        List<TPlan> tPlans = planService.selectByTaskId(taskId);
        map.put("task", tTask);
        map.put("plans", tPlans);
        return map;
    }


    //修改任务状态为已完成状态
    @RequestMapping("updateTaskInfo")
    public Map updateTaskInfo(Integer taskId){
        Map<String, Object> map = new HashMap<>();
        //先查询再修改
        TTask tTask = taskService.queryDescTask(taskId);
        tTask.setStatus("已完成");
        int i = taskService.updateByTask(tTask);
//        taskService.updateTaskStausByTaskId(taskId,status);
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }

    //查询所有的任务
    @RequestMapping("queryAllTask")
    public Map queryAllTask(){
        Map<String, Object> map = new HashMap<>();
        List<TTask> tTasks = taskService.queryAllTask();
        map.put("tasks", tTasks);
        return map;
    }
    //测试
    @RequestMapping("test")
    public Map Test(){
        Map<String, java.lang.Object> map = new HashMap<>();
//        System.out.println(pageNum+""+pageSize+""+order);
        PageInfo pageInfo = taskService.queryTask(5, 1,"task_id");
        map.put("pageInfo",pageInfo);
        return map;
    }


}

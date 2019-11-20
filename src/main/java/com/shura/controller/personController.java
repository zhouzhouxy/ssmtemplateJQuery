package com.shura.controller;

import com.github.pagehelper.PageInfo;
import com.shura.entity.TEmployee;
import com.shura.entity.TRole;
import com.shura.service.EmployeeService;
import com.shura.service.RoleService;
import com.shura.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("personController")
public class personController {

    @Autowired
    @Resource
    private EmployeeService employeeService=null;

    @Autowired
    @Resource
    private TaskService taskService=null;

    @Autowired
    @Resource
    private RoleService roleService=null;


    @RequestMapping("queryPerson")
    public Map queryPerson(Integer pageNum,Integer pageSize,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //获取存在session中的值
        TEmployee employee = (TEmployee) request.getSession().getAttribute("employee");
        //查询下属员工
//        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(employee);
        PageInfo pageInfo = employeeService.queryEmployeeListById(pageNum, pageSize, employee.getEmployeeId());
        map.put("pageInfo", pageInfo);
        return map;
    }
    //查询员工的详细信息
    @RequestMapping("queryDetailEmployee")
    public Map queryDetailEmployee(Integer employeeId){
        Map<String, Object> map = new HashMap<>();
        //查询
        TEmployee tEmployee = employeeService.queryEmployeeById(employeeId);
        map.put("employee", tEmployee);
        return map;
    }

    //分页查询登录员工的任务
    @RequestMapping("queryTask")
    public Map queryTaskBySessionId(Integer pageNum,Integer pageSize,String keyWord,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        //获取存在session中的员工
        TEmployee employee = (TEmployee)request.getSession().getAttribute("employee");
        //查询该员工的parentId
        TEmployee employee1 = employeeService.queryEmployeeById(employee.getEmployeeId());
        //查询所有的employee
        List<TEmployee> tEmployees = employeeService.selectAll();
        map.put("employees", tEmployees);
        PageInfo pageInfo = taskService.selectEmpTask(pageNum, pageSize, keyWord, employee.getEmployeeId());
        map.put("pageInfo", pageInfo);
        return map;
    }

    //查询所有的员工和角色
    @RequestMapping("queryAllEmployee")
    public Map queryAllEmployee(Integer pageNum,Integer pageSize,String keyWord){
        Map<Object, Object> map = new HashMap<>();
        PageInfo pageInfo = employeeService.queryAll(pageNum, pageSize, keyWord);
        map.put("pageInfo", pageInfo);

        List<TRole> tRoles = roleService.queryAll();

        map.put("role", tRoles);
        return map;
    }
    //查询领导
    @RequestMapping("queryLeaderShip")
    public Map queryLeaderShip(Integer roleId){
       Map<Object, Object> map = new HashMap<>();
       if(roleId-1>0) {
           List<TEmployee> tEmployees = employeeService.queryLeader(roleId);
           map.put("leader",tEmployees);
       }else{
           map.put("leader",null);
       }
       return map;
    }

    //添加员工
    @RequestMapping("addEmp")
    public Map addEmp(String employeeName, String realName, String password, String confirm_password
                        , String sex, String birthday, String duty, String enrolldate, String education,
                      String major, String experience, Integer roleId, Integer parentId){

        //将字符串转换为date类型

/*        //转换时间格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp timestamp = Timestamp.valueOf(format.format(birthday));
        Timestamp timestamp2 = Timestamp.valueOf(format.format(enrolldate));*/

        if(parentId==0){
            parentId=null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

//
//        TEmployee employee = new TEmployee(null, employeeName, password, realName
//                , sex, birthday, duty, enrolldate, education, major, experience
//                , roleId, parentId);
//        int i = employeeService.insertEmp(employee);
        Map<Object, Object> map = new HashMap<>();
//        if(i>0){
//           map.put("message", "yes");
//        }
        return map;
    }

    @RequestMapping("delEmp")
    public Map delEmp(HttpServletRequest request){
        String ids = request.getParameter("ids");
        String[] split = ids.split(",");
        Integer[] taskIds=new Integer[split.length];
        for(int i=0;i<split.length;i++){
            taskIds[i]=new Integer(split[i]);
        }
        HashMap<String, Object> map = new HashMap<>();
        int i = employeeService.deleteEmp(taskIds);
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }

    @RequestMapping("queryLeader")
    public Map queryLeader(){
        List<TEmployee> tEmployees = employeeService.queryLeader(3);
        Map<Object, Object> map = new HashMap<>();
        map.put("employees", tEmployees);
        return map;
    }

    //修改Emp
    //localhost:8080/personController/updateEmp?employeeId=4&birthday=&duty=Android&enrolldate=&education=Android&major=Android
    // &experience=Android&roleId=3&parentId=9
    @RequestMapping("updateEmp")
    public Map updateEmp(Integer employeeId,String birthday,String duty,String enrolldate,String education,String major,
                         String experience,Integer roleId,Integer parentId){
        Map<Object, Object> map = new HashMap<>();
        //将数据插入表中
        //先查询在修改
        TEmployee employee1 = employeeService.queryEmployeeById(employeeId);

        TEmployee employee = new TEmployee(employeeId, null,null, null
                , null, birthday, duty, enrolldate, education, major, experience
                , roleId, parentId);
        employee1.setRoleId(roleId);
        employee1.setParentId(parentId);
        employee1.setMajor(major);
        employee1.setExperience(experience);
        employee1.setEducation(education);
        int i = employeeService.updateByEmp(employee1);
        if(i>0){
            map.put("message", "yes");
        }
        return map;
    }
}

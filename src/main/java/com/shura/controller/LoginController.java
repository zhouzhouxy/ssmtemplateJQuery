package com.shura.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shura.entity.TEmployee;
import com.shura.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("login")
public class LoginController {

    @Autowired
    @Resource
    private EmployeeService employeeService=null;


    //地址栏输入跳转到登录页面
    @RequestMapping("index")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }
    //检查用户名输入
    @RequestMapping("verify")
    public Map verify(String roleName, String pwd, Integer roleId, HttpServletRequest request){
//        System.out.println(roleName+"=====>"+pwd+"====>"+roleId);
        Map<String,Object> map=new HashMap<>();
        TEmployee tEmployee = new TEmployee();
        tEmployee.setEmployeeName(roleName);
        tEmployee.setRoleId(Integer.valueOf(roleId));
        tEmployee.setPassword(pwd);
        TEmployee tEmployee1 = employeeService.selectByEmployee(tEmployee);
//        System.out.println(tEmployee1);
        if(tEmployee1!=null){
            map.put("message", "success");
            request.getSession().setAttribute("employee", tEmployee1);
        }else{
            map.put("message","fail");
        }
        return map;
    }
    @RequestMapping("SignOut")
    public ModelAndView SignOut(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        ModelAndView mv = new ModelAndView();
        //设置跳转页面的名称
        mv.setViewName("login");
        return mv;
    }

}

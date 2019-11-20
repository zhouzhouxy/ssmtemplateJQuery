package com.shura.controller;

import com.alibaba.fastjson.JSON;
import com.shura.entity.TEmployee;
import com.shura.entity.TMenu;
import com.shura.service.EmployeeService;
import com.shura.service.MenuService;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("main")
public class MainPageController {

    @Autowired
    @Resource
    private MenuService menuService=null;

    @Autowired
    @Resource
    private EmployeeService employeeService=null;


    //跳转到主页
    @RequestMapping("main")
    public ModelAndView mainPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("main/main");
        return mv;
    }
    //跳转页面
    //通过存在session中的tyEmployee查询显示在主页面
    @RequestMapping("queryData")
    public Map showData(HttpServletRequest request){
        //查询session
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
//        System.out.println(tEmployee);
        Map<String,Object> map=new HashMap<>();
        List<TMenu> tMenus = menuService.selectByRoleId(tEmployee.getRoleId());
        //将session的对象传递给前端
        map.put("employee", tEmployee);
        String s = JSON.toJSONString(tMenus);
        map.put("menus", s);
//        System.out.println(s);
        return map;
    }

    @RequestMapping("taskManager")
    public ModelAndView taskManager(){
        ModelAndView mv = new ModelAndView();
//        System.out.println("进入了任务管理界面");
        mv.setViewName("main/taskManager");
        return mv;
    }

    @RequestMapping("queryEmp")
    public ModelAndView queryEmployee(HttpServletRequest request){
       // 通过获取存在session中的id查询下属
        TEmployee tEmployee=(TEmployee)request.getSession().getAttribute("employee");
       // 查询所有下属
        List<TEmployee> tEmployees = employeeService.selectByEmployeeList(tEmployee);
       // 转成json格式
        String s = JSON.toJSONString(tEmployee);
        ModelAndView mv = new ModelAndView();
        mv.addObject("employee",s);
        mv.setView(new MappingJackson2JsonView());
        return mv;
    }

}

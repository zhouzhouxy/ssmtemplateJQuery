package com.shura.service;

import com.github.pagehelper.PageInfo;
import com.shura.entity.TEmployee;

import java.util.List;

public interface EmployeeService {
    //根据登录的用户名密码查询员工
    TEmployee selectByEmployee(TEmployee employee);

    //查询所有下属
    List<TEmployee> selectByEmployeeList(TEmployee employee);

    TEmployee queryEmployeeById(Integer eid);

    PageInfo queryEmployeeListById(Integer pageNum,Integer pageSize,Integer eId);

    //查询所有的employee对象
    List<TEmployee> selectAll();

    PageInfo queryAll(Integer pageNum,Integer pageSize,String keyWord);

    List<TEmployee> queryLeader(Integer roleId);

    //插入
    int insertEmp(TEmployee employee);

    //删除
    int deleteEmp(Integer[] ints);

    //修改
    int updateByEmp(TEmployee employee);
}

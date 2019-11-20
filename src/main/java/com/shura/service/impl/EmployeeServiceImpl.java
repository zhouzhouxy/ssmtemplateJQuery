package com.shura.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shura.entity.TEmployee;
import com.shura.entity.TEmployeeExample;
import com.shura.mapper.TEmployeeMapper;
import com.shura.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private TEmployeeMapper employeeMapper=null;

    public TEmployeeMapper getEmployeeMapper() {
        return employeeMapper;
    }

    public void setEmployeeMapper(TEmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public TEmployee selectByEmployee(TEmployee employee) {
        return  employeeMapper.selectByEmployee(employee);
    }

    @Override
    public List<TEmployee> selectByEmployeeList(TEmployee employee) {
        TEmployeeExample example = new TEmployeeExample();
        TEmployeeExample.Criteria criteria = example.createCriteria();
//        System.out.println(employee.getRoleId()+"存在session中的值");
        criteria.andParentIdEqualTo(employee.getEmployeeId());
        return employeeMapper.selectByExample(example);
    }

    @Override
    public TEmployee queryEmployeeById(Integer eid) {
        return employeeMapper.selectByPrimaryKey(eid);
    }

    @Override
    public PageInfo queryEmployeeListById(Integer pageNum,Integer pageSize,Integer eId) {
        PageHelper.startPage(pageNum, pageSize);
        TEmployeeExample example = new TEmployeeExample();
        TEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(eId);
        List<TEmployee> tEmployees = employeeMapper.selectByExample(example);
        PageInfo<TEmployee> pageInfo = new PageInfo<>(tEmployees);
        return pageInfo;
    }

    @Override
    public List<TEmployee> selectAll() {
        return employeeMapper.selectByExample(null);
    }

    @Override
    public PageInfo queryAll(Integer pageNum, Integer pageSize, String keyWord) {
        //分页
        PageHelper.startPage(pageNum, pageSize);
        //设置排序
        TEmployeeExample example=new TEmployeeExample();
        example.setOrderByClause(keyWord);
        List<TEmployee> tEmployees = employeeMapper.selectByExample(example);
        PageInfo<TEmployee> pageInfo = new PageInfo<>(tEmployees);
        return pageInfo;
    }

    @Override
    public List<TEmployee> queryLeader(Integer roleId) {
        TEmployeeExample example=new TEmployeeExample();
        TEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andRoleIdEqualTo(roleId-1);
        List<TEmployee> tEmployees = employeeMapper.selectByExample(example);
        return tEmployees;
    }

    @Override
    public int insertEmp(TEmployee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public int deleteEmp(Integer[] ints) {
        TEmployeeExample example=new TEmployeeExample();
        TEmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdIn(Arrays.asList(ints));
        int i = employeeMapper.deleteByExample(example);
        if(i>0){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateByEmp(TEmployee employee) {
        int i = employeeMapper.updateByPrimaryKey(employee);
        if(i>0){
            return i;
        }
        return 0;
    }
}

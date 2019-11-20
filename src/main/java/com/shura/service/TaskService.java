package com.shura.service;

import com.github.pagehelper.PageInfo;
import com.shura.entity.TTask;
import javafx.concurrent.Task;

import java.util.List;

public interface TaskService {
    //插入数据
    int insert(TTask task);

    //分页查询
    PageInfo queryTask(Integer pageSize, Integer pageNum,String order);

    //根据taskId查询任务详情信息
    TTask queryDescTask(Integer taskId);

    //分页查询不同的任务状态
    PageInfo selectUnimplmentorTask( Integer pageNum,Integer pageSize,String order,String status);

    //根据任务id删除
    int deleteByTaskIds(Integer[] taskId);

    //根据task修改
    int updateByTask(TTask task);

    //修改task任务状态
    int updateTaskStatusByTaskId(Integer taskId,String status);

    //分页查询登录员工的所有任务
    PageInfo selectEmpTask(Integer pageNum,Integer pageSize,String order, Integer eid);

    //查询所有的任务
    List<TTask> queryAllTask();
}

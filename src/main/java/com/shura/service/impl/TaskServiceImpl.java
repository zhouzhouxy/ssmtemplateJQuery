package com.shura.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shura.entity.TTask;
import com.shura.entity.TTaskExample;
import com.shura.mapper.TTaskMapper;
import com.shura.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private TTaskMapper taskMapper=null;

    public TTaskMapper getTaskMapper() {
        return taskMapper;
    }

    public void setTaskMapper(TTaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public int insert(TTask task) {
        return taskMapper.insert(task);
    }

    @Override
    public PageInfo queryTask(Integer pageSize, Integer pageNum,String order) {
//        System.out.println("pageSize="+pageSize+";pageNum="+pageNum+";order="+order);
        PageHelper.startPage(pageNum, pageSize);
        TTaskExample tte = new TTaskExample();

        tte.setOrderByClause(order);
        List<TTask> tTasks = taskMapper.selectByExample(tte);
//        System.out.println(tTasks);
        PageInfo<TTask> tTaskPageInfo = new PageInfo<>(tTasks);
        return tTaskPageInfo;
    }

    @Override
    public TTask queryDescTask(Integer taskId) {
//        TTask tTask = taskMapper.selectByPrimaryKey(taskId);
        return taskMapper.selectByPrimaryKey(taskId);
    }

    @Override
    public PageInfo selectUnimplmentorTask( Integer pageNum,Integer pageSize, String order, String status) {
        //分页查询
        PageHelper.startPage(pageNum,pageSize);
        TTaskExample example = new TTaskExample();
        example.setOrderByClause(order);    //根据指定字段排序
        TTaskExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(status);  //根据指定的状态查询
        //根据条件查询
        List<TTask> tTasks = taskMapper.selectByExample(example);

        PageInfo<TTask> pageInfo = new PageInfo<>(tTasks);
        return pageInfo;
    }

    @Override
    public int deleteByTaskIds(Integer[] taskIds) {
        int i=0;
        for (Integer taskId:taskIds) {
           i = taskMapper.deleteByPrimaryKey(taskId);
        }
        return i;
    }

    @Override
    public int updateByTask(TTask task) {
        return  taskMapper.updateByPrimaryKey(task);
    }

    @Override
    public int updateTaskStatusByTaskId(Integer taskId, String status) {

        return 0;
    }

    @Override
    public PageInfo selectEmpTask(Integer pageNum, Integer pageSize, String order, Integer eid) {
        PageHelper.startPage(pageNum, pageSize);
        TTaskExample tTaskExample = new TTaskExample();
        //根据order排序
        tTaskExample.setOrderByClause(order);
        TTaskExample.Criteria criteria = tTaskExample.createCriteria();
        criteria.andImplementorIdEqualTo(eid);
//        criteria.andAssignerIdEqualTo(eid);
        List<TTask> tTasks = taskMapper.selectByExample(tTaskExample);
        PageInfo<TTask> pageInfo = new PageInfo<>(tTasks);
        return pageInfo;
    }

    @Override
    public List<TTask> queryAllTask() {
        return taskMapper.selectByExample(null);
    }
}

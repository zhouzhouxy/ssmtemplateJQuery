package com.shura.service;

import com.github.pagehelper.PageInfo;
import com.shura.entity.Item;

public interface ItemService {
    //查询
    public PageInfo queryItem(Integer pageNum,Integer pageSize,Item item);

    public PageInfo queryItems(Integer pageNum,Integer pageSize,String item);

    //删除
    public void deleteItemsById(int id);

    //查询全部
    public Integer queryTotal();

    //插入
    public void insertItem(Item item);
}

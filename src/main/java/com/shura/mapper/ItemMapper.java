package com.shura.mapper;

import com.shura.entity.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemMapper {
    public List<Item> queryItem(Item item);

    public List<Item> queryItems(String str);

    //删除deleteItemsById
    public void deleteItemsById(@Param("id") Integer id);

    public Integer queryTotal();

    //插入
    public void insertItem(Item item);

}

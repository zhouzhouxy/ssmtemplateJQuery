package com.shura.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shura.entity.Item;
import com.shura.mapper.ItemMapper;
import com.shura.service.ItemService;

import java.util.List;
import java.util.Map;

public class ItemSeviceImpl implements ItemService {
    private ItemMapper itemMapper;

    public ItemMapper getItemMapper() {
        return itemMapper;
    }

    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public PageInfo queryItem(Integer pageNum, Integer pageSize, Item item) {
        List<Item> items = itemMapper.queryItem(item);
        System.out.println("items"+items);
//        PageHelper.startPage(pageNum, pageSize);
//        List<Item> items1 = itemMapper.queryItem(item);
//        System.out.println("items1"+items1);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }

    @Override
    public PageInfo queryItems(Integer pageNum,Integer pageSize,String item) {
        PageHelper.startPage(pageNum, pageSize);
        List<Item> items = itemMapper.queryItems(item);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        System.out.println(pageInfo);
        return pageInfo;
    }

    @Override
    public void deleteItemsById(int id) {
        itemMapper.deleteItemsById(id);
    }

    @Override
    public Integer queryTotal() {
        return itemMapper.queryTotal();
    }

    @Override
    public void insertItem(Item item) {
        itemMapper.insertItem(item);
    }
}

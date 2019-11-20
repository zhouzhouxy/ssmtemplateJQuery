package com.shura.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shura.entity.Item;
import com.shura.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("test")
public class TestController {
    @Autowired
    @Qualifier("itemService")
    private ItemService itemService;

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping("test1")
    public Map test1(@RequestBody String str){
        System.out.println(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        Object pageSize = jsonObject.get("pageSize");
        Object pageNum = jsonObject.get("pageNum");
        Object searchCondition = jsonObject.get("searchCondition");
//        System.out.println(searchCondition);
        if (searchCondition.toString().trim().length()==0){
            searchCondition="";
        }
        if(pageSize==null){
            pageSize=1;
        }
        if(pageNum==null){
            pageNum=10;
        }
        System.out.println(pageSize+"====>"+pageNum+"=======>"+searchCondition);

//        Item item = new Item();
//        PageInfo pageInfo = itemService.queryItem(1, 10, item);
        PageInfo pageInfo = itemService.queryItems(Integer.valueOf(pageNum.toString()),Integer.valueOf(pageSize.toString()),searchCondition.toString());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("message","success");
        map.put("pageInfo",pageInfo);
        return map;
    }
    @RequestMapping("delete")
    public  Map delete(@RequestBody String str){
        JSONObject jsonObject = JSONObject.parseObject(str);
        System.out.println(jsonObject);
        Object pageSize = jsonObject.get("pageSize");
        Object pageNum = jsonObject.get("pageNum");
        Object searchCondition = jsonObject.get("searchCondition");
        Object id = jsonObject.get("id");

        //根据id删除
        itemService.deleteItemsById(Integer.valueOf(id.toString()));

//        System.out.println(searchCondition);
        if (searchCondition.toString().trim().length()==0){
            searchCondition="";
        }
        if(pageSize==null){
            pageSize=1;
        }
        if(pageNum==null){
            pageNum=10;
        }
//        System.out.println(pageSize+"====>"+pageNum+"=======>"+searchCondition);

        Item item = new Item();
//        PageInfo pageInfo = itemService.queryItem(1, 10, item);
        PageInfo pageInfo = itemService.queryItems(Integer.valueOf(pageNum.toString()),Integer.valueOf(pageSize.toString()),searchCondition.toString());
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("message","success");
        map.put("pageInfo",pageInfo);

        //在查询
        return map;
    }
    //插入
    @RequestMapping("insert")
    public Map insert(@RequestBody String str){
        System.out.println(str);
        JSONObject jsonObject = JSONObject.parseObject(str);
        String id = jsonObject.get("id").toString();
        String title = jsonObject.get("title").toString();
        String price = jsonObject.get("price").toString();
        String brand = jsonObject.get("brand").toString();
        Float aFloat = Float.valueOf(price);
        Item item = new Item(Integer.valueOf(id), title, aFloat, brand);

        Map<String,Object> map=new HashMap<>();

        itemService.insertItem(item);

        Integer integer = itemService.queryTotal();

//        itemService.query

        map.put("total",integer);
        return map;
    }
}

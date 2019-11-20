package com.shura.service;

import com.shura.entity.TMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService {
    //根据角色id查询所有数据
    List<TMenu> selectByRoleId(@Param("id") Integer id);
}

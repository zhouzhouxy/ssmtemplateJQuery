package com.shura.mapper;

import com.shura.entity.TMenu;

import java.util.List;

public interface MenuMapper {
    List<TMenu> queryMenuByRoleId(Integer roleId);
}

package com.shura.service.impl;

import com.shura.entity.TMenu;
import com.shura.entity.TMenuExample;
import com.shura.mapper.MenuMapper;
import com.shura.mapper.TMenuMapper;
import com.shura.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuMapper menuMapper;

    public MenuMapper getMenuMapper() {
        return menuMapper;
    }

    public void setMenuMapper(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<TMenu> selectByRoleId(Integer id) {
        System.out.println(id);
        return   menuMapper.queryMenuByRoleId(id);
    }

}

package com.shura.service.impl;

import com.shura.entity.TRole;
import com.shura.mapper.TRoleMapper;
import com.shura.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private TRoleMapper roleMapper;

    public TRoleMapper getRoleMapper() {
        return roleMapper;
    }

    public void setRoleMapper(TRoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<TRole> queryAll() {
        List<TRole> tRoles = roleMapper.selectByExample(null);
//        List<TRole> tRoles = roleMapper.selectAll();
        return tRoles;
    }
}

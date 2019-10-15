package com.esen.swglpt.service.impl;

import com.esen.swglpt.entity.Role;
import com.esen.swglpt.entity.RoleMenu;
import com.esen.swglpt.mapper.RoleMapper;
import com.esen.swglpt.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role selectByPrimaryKey(Short roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }

    @Override
    public List<Role> list() {
        return roleMapper.selectAllList();
    }

}

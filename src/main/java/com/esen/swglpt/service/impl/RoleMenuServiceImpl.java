package com.esen.swglpt.service.impl;

import com.esen.swglpt.entity.RoleMenu;
import com.esen.swglpt.mapper.RoleMenuMapper;
import com.esen.swglpt.service.RoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public void save(RoleMenu roleMenu) {
        roleMenuMapper.insert(roleMenu);
    }
}

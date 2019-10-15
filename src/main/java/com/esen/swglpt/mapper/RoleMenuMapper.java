package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.RoleMenu;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
}
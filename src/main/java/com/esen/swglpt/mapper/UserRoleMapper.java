package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
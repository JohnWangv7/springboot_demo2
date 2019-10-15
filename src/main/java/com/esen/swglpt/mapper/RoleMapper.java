package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Short roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Short roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectRoleByUserId(Long userId);

    List<Role> selectAllList();
}
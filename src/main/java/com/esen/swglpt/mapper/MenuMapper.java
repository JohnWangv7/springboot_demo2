package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.Menu;

import java.util.List;

public interface MenuMapper {
    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectMenuByRoleId(Long roleId);

    List<Menu> selctAllList();
}
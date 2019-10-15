package com.esen.swglpt.service;

import com.esen.swglpt.entity.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> selectMenuByRoleId(Long roleId);

    List<Menu> list();
}

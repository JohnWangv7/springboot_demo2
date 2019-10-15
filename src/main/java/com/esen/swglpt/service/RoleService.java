package com.esen.swglpt.service;

import com.esen.swglpt.entity.Role;
import com.esen.swglpt.entity.RoleMenu;

import java.util.List;

public interface RoleService {

    Role selectByPrimaryKey(Short roleId);

    List<Role> selectRoleByUserId(Long userId);

    List<Role> list();

}

package com.esen.swglpt.service.impl;

import com.esen.swglpt.entity.Menu;
import com.esen.swglpt.mapper.MenuMapper;
import com.esen.swglpt.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> selectMenuByRoleId(Long roleId) {
        return menuMapper.selectMenuByRoleId(roleId);
    }

    @Override
    public List<Menu> list() {
        return menuMapper.selctAllList();
    }
}

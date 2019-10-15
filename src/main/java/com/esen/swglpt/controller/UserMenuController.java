package com.esen.swglpt.controller;

import com.esen.swglpt.common.utils.ShiroUtils;
import com.esen.swglpt.entity.Menu;
import com.esen.swglpt.entity.Role;
import com.esen.swglpt.entity.RoleMenu;
import com.esen.swglpt.entity.User;
import com.esen.swglpt.service.MenuService;
import com.esen.swglpt.service.RoleMenuService;
import com.esen.swglpt.service.RoleService;
import com.esen.swglpt.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限测试
 *
 * @author wangdong
 */
@Api(value = "用户菜单控制器", tags = "用户菜单控制器")
@RestController
@RequestMapping("/menu")
public class UserMenuController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 获取用户信息集合
     */
    @RequestMapping(value = "/getUserInfoList", method = RequestMethod.GET)
    @RequiresPermissions("user:info")
    public Map<String, Object> getUserInfoList() {
        HashMap<String, Object> map = new HashMap<>(1);
        List<User> userList = userService.list();
        map.put("userList", userList);
        return map;
    }

    /**
     * 获取角色信息集合
     */
    @RequestMapping(value = "getRoleInfoList", method = RequestMethod.GET)
    @RequiresPermissions("role:info")
    public Map<String, Object> getRoleInfoList() {
        HashMap<String, Object> map = new HashMap<>(1);
        List<Role> roleList = roleService.list();
        map.put("roleList", roleList);
        return map;
    }

    /**
     * 获取权限信息集合
     */
    @RequestMapping(value = "/getMenuInfoList", method = RequestMethod.GET)
    @RequiresPermissions("menu:info")
    public Map<String, Object> getMenuInfoList() {
        Map<String, Object> map = new HashMap<>(1);
        List<Menu> menuList = menuService.list();
        map.put("menuList", menuList);
        return map;
    }

    /**
     * 获取所有数据
     */
    @RequestMapping(value = "/getInfoAll", method = RequestMethod.GET)
    @RequiresPermissions("info:all")
    public Map<String, Object> getInfoAll() {
        Map<String, Object> map = new HashMap<>(3);
        List<User> userList = userService.list();
        map.put("userList", userList);
        List<Role> roleList = roleService.list();
        map.put("roleList", roleList);
        List<Menu> menuList = menuService.list();
        map.put("menuList", menuList);
        return map;
    }

    /**
     * 添加管理员角色权限(测试动态权限更新)
     */
    @RequestMapping(value = "/addMenu", method = RequestMethod.GET)
    public Map<String, Object> addMenu(RoleMenu roleMenu) {
        //添加管理员角色权限
        roleMenuService.save(roleMenu);
        //清除缓存
        String username = "admin";
        ShiroUtils.deleteCache(username, false);
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "权限添加成功");
        return map;
    }
}

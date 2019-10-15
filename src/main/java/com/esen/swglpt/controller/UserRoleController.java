package com.esen.swglpt.controller;

import com.esen.swglpt.service.MenuService;
import com.esen.swglpt.service.RoleMenuService;
import com.esen.swglpt.service.RoleService;
import com.esen.swglpt.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 角色测试
 *
 * @author wangdong
 */
@Api(value = "用户角色权限控制器", tags = "用户角色权限控制器")
@RestController
@RequestMapping(value = "/role")
public class UserRoleController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Resource
    private RoleMenuService roleMenuService;

    /**
     * 管理员角色测试接口
     */
    @RequestMapping(value = "/getAdminInfo", method = RequestMethod.GET)
    @RequiresRoles("ADMIN")
    public Map<String, Object> getAdminInfo() {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "这里是只有管理员角色能访问的接口");
        return map;
    }

    /**
     * 用户角色测试接口
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @RequiresRoles("USER")
    public Map<String, Object> getUserInfo() {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "这里是只有用户角色能访问的接口");
        return map;
    }

    /**
     * 角色测试接口
     */
    @RequestMapping(value = "/getRoleInfo", method = RequestMethod.GET)
    @RequiresRoles(value = {"ADMIN", "USER"}, logical = Logical.OR)
    public Map<String, Object> getRoleInfo() {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "这里是只要有Admin或User角色能访问的接口");
        return map;
    }

}

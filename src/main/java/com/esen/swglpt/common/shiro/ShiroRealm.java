package com.esen.swglpt.common.shiro;

import com.esen.swglpt.common.utils.ShiroUtils;
import com.esen.swglpt.entity.Menu;
import com.esen.swglpt.entity.Role;
import com.esen.swglpt.entity.User;
import com.esen.swglpt.service.MenuService;
import com.esen.swglpt.service.RoleService;
import com.esen.swglpt.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro权限匹配和账户密码匹配
 *
 * @author ''
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    /**
     * 授权权限
     * 用户进行权限验证时候Shiro会去缓存中找，如果查不到数据，会执行这个方法去查权限，并放入缓存中
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        // 获取用户ID
        Long userId = user.getUserId();
        // 这里可以进行授权和处理
        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();
        // 查询角色和权限
        List<Role> roleList = roleService.selectRoleByUserId(userId);
        for (Role role : roleList) {
            rolesSet.add(role.getRoleName());
            List<Menu> menuList = menuService.selectMenuByRoleId(role.getRoleId());
            for (Menu menu : menuList) {
                permsSet.add(menu.getPerms());
            }
        }
        // 将查到的权限和角色分别传去authenticationInfo中
        authorizationInfo.setStringPermissions(permsSet);
        authorizationInfo.setRoles(rolesSet);
        return authorizationInfo;
    }

    /**
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 输入用户输入的账号
        String username = (String) authenticationToken.getPrincipal();
        // 通过username从数据库中查找User对象，如果找到进行验证
        User user = userService.selectUserByName(username);
        // 判断账号是否存在
        if (user == null) {
            throw new AuthenticationException();
        }
        // 判断账号是否被冻结
        if (user.getStatus() == null || user.getStatus() == 2) {
            throw new LockedAccountException();
        }
        // 进行验证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户名
                user,
                // 密码
                user.getPassword(),
                // 设置盐值
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        // 验证成功开始踢人（清除缓存和Session）
        ShiroUtils.deleteCache(username, true);
        return authenticationInfo;
    }
}

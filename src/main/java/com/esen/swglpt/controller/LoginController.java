package com.esen.swglpt.controller;

import com.esen.swglpt.common.utils.ShiroUtils;
import com.esen.swglpt.entity.User;
import com.esen.swglpt.service.UserService;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangdong
 * 登录
 */
@Controller
@Api(value = "登录控制器", tags = "登录控制器")
@RequestMapping(value = "/user")
public class LoginController {

    @Resource
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @ApiOperation(value = "跳转登录首页", httpMethod = "GET", notes = "登录首页")
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST", notes = "用户登录身份验证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "账号", required = true, paramType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "String")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "登录成功!"),
            @ApiResponse(code = 500, message = "服务器内部错误 :(")})
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginIn(User user) {
        Map<String, Object> map = new HashMap<>(3);
        // 进行身份验证
        try {
            // 验证身份和登录
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            map.put("code", 500);
            map.put("msg", "用户不存在或者密码错误!");
            return map;
        } catch (LockedAccountException e) {
            map.put("code", 500);
            map.put("msg", "登录失败，该用户已被冻结!");
            return map;
        } catch (AuthenticationException e) {
            map.put("code", 500);
            map.put("msg", "未知异常!");
            return map;
        }
        map.put("code", 200);
        map.put("msg", "登录成功!");
        map.put("token", ShiroUtils.getSession().getId().toString());
        return map;

        /*User user = userService.findUserByAccountAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getId());
            redisTemplate.opsForValue().set("loginUser:" + user.getId(), session.getId());
            return "login success!";
        } else {
            return "username or password is error!";
        }*/
    }

    /**
     * 未登录
     */
    @ApiOperation(value = "未登录", httpMethod = "POST", notes = "未登录")
    @RequestMapping(value = "/unauth", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> unauth() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", 500);
        map.put("msg", "未登录!");
        return map;
    }

    /**
     * 登出（测试登出）
     */
    @ApiOperation(value = "登出", httpMethod = "GET", notes = "登出（测试登出）")
    @RequestMapping(value = "/getLogout", method = RequestMethod.GET)
    @ResponseBody
    @RequiresUser
    public Map<String, Object> getLogout() {
        ShiroUtils.loginOut();
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("code", 200);
        map.put("msg", "登出");
        return map;
    }

    @ApiOperation(value = "跳转注册首页", httpMethod = "GET", notes = "注册首页")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "用户注册")
    @RequestMapping(value = "/userRegister", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> userRegister(User user) {
        Map<String, Object> map = new HashMap<>(2);
        try {
            if (StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword())) {
                map.put("code", 500);
                map.put("msg", "账号密码不可为空!");
            } else {
                userService.insertSelective(user);
                map.put("code", 200);
                map.put("msg", "注册成功!");
            }
        } catch (Exception e) {
            map.put("code", 500);
            map.put("msg", "用户注册异常!");
        }
        return map;
    }

    @ApiOperation(value = "根据用户ID获取用户信息", httpMethod = "POST", notes = "获取用户信息")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    @Cacheable(value = "userId")
    public String getUserInfo(Long userId) {
        User user = userService.findUserByUserId(userId);
        if (user != null) {
            return "find success!" + user.toString();
        } else {
            return "user is not login!";
        }
    }

    @RequestMapping(value = "/sessionid/get")
    @ResponseBody
    public Map<String, Object> getPort() {
        Map<String, Object> map = new HashMap<>(2);
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        int port = request.getLocalPort();
        String sessionId = request.getSession().getId();
        map.put("port", port);
        map.put("sessionID", sessionId);
        return map;
    }
}

package com.esen.swglpt.controller;

import com.esen.swglpt.entity.User;
import com.esen.swglpt.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wangdong
 * 登录
 */
@Controller
@Api(value = "用户类控制器", tags = "用户类控制器")
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/loginIn")
    @ResponseBody
    public String loginIn(HttpServletRequest request, String username, String password) {
        User user = userService.findUserByAccountAndPassword(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getId());
            redisTemplate.opsForValue().set("loginUser:" + user.getId(), session.getId());
            return "login success!";
        } else {
            return "username or password is error!";
        }
    }

    @RequestMapping(value = "/getUserInfo")
    @ResponseBody
    public String get(Long userId) {
        User user = userService.findUserByUserId(userId);
        if (user != null) {
            return "find success!" + user.toString();
        } else {
            return "user is not login!";
        }
    }
}

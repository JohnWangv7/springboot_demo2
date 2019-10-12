package com.esen.swglpt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangdong
 * 登录
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
}

package com.esen.swglpt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdong
 * home
 * */
@RestController
public class HomeController {

    @RequestMapping(value = "/index")
    public String home() {
        return "home index";
    }
}

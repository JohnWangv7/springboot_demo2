package com.esen.swglpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdong
 * home
 */
@RestController
@Api(value = "首页控制器", tags = "首页控制器")
public class HomeController {

    @ApiOperation(value = "首页", httpMethod = "GET", notes = "系统首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home() {
        return "home index";
    }
}

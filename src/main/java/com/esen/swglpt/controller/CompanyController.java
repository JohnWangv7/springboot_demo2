package com.esen.swglpt.controller;

import com.esen.swglpt.entity.Company;
import com.esen.swglpt.service.CompanyService;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 */
@Controller
@Api(value = "公司类控制器", tags = {"公司操作 api示例"})
public class CompanyController {

    @Resource
    CompanyService companyService;

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询公司列表", notes = "公司列表详细信息")
    @ApiResponses({@ApiResponse(code = 200, message = "成功 :)"),
            @ApiResponse(code = 300, message = "请求重定向"),
            @ApiResponse(code = 400, message = "服务器不理解的请求语法:("),
            @ApiResponse(code = 404, message = "未找到网页 :("),
            @ApiResponse(code = 500, message = "服务器内部错误 :(")})
    public List<Company> selectAll() {
        return companyService.selectAll();
    }
}

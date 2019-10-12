package com.esen.swglpt.controller;

import com.esen.swglpt.entity.Company;
import com.esen.swglpt.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class CompanyController {

    @Resource
    CompanyService companyService;

    @RequestMapping(value = "/selectAll")
    @ResponseBody
    public List<Company> selectAll() {
        return companyService.selectAll();
    }
}

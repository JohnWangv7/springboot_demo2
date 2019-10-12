package com.esen.swglpt.service.impl;

import com.esen.swglpt.entity.Company;
import com.esen.swglpt.mapper.CompanyMapper;
import com.esen.swglpt.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    CompanyMapper companyMapper;

    @Override
    public List<Company> selectAll() {
        return companyMapper.selectAll();
    }
}

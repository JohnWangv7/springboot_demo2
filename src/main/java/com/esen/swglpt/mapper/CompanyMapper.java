package com.esen.swglpt.mapper;

import com.esen.swglpt.entity.Company;

import java.util.List;

public interface CompanyMapper {
    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectAll();
}
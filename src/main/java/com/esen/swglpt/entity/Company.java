package com.esen.swglpt.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wangdong
 * <p>
 * 公司类
 */
@Data
@ApiModel(value = "Company", description = "公司类")
public class Company implements Serializable {

    @ApiModelProperty(required = true, name = "companyId", value = "公司ID", dataType = "Integer", example = "2000110")
    private Integer companyId;

    @ApiModelProperty(required = true, name = "companyName", value = "公司名称", dataType = "String")
    private String companyName;

    @ApiModelProperty(required = true, name = "companyAddress", value = "公司地址", dataType = "String")
    private String companyAddress;

    @ApiModelProperty(required = true, name = "companyDescription", value = "公司描述", dataType = "String")
    private String companyDescription;

    @ApiModelProperty(required = true, name = "companyPeopleNum", value = "公司人数", dataType = "Integer")
    private Integer companyPeopleNum;
}
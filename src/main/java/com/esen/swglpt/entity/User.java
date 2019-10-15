package com.esen.swglpt.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangdong
 * <p>
 * 用户类
 */
@Data
@ApiModel(value = "User", description = "用户类")
public class User implements Serializable {

    @ApiModelProperty(required = true, name = "userId", value = "用户ID", dataType = "Integer", example = "2000110")
    private Long userId;

    @ApiModelProperty(required = true, name = "username", value = "账户", dataType = "String")
    private String username;

    @ApiModelProperty(required = true, name = "password", value = "密码", dataType = "String")
    private String password;

    @ApiModelProperty(name = "mobile", value = "手机号", dataType = "String")
    private String mobile;

    @ApiModelProperty(name = "email", value = "E-mail", dataType = "String")
    private String email;

    @ApiModelProperty(name = "sex", value = "性别", dataType = "String")
    private String sex;

    @ApiModelProperty(name = "roleId", value = "角色ID", dataType = "Integer")
    private Long roleId;

    @ApiModelProperty(name = "userGroup", value = "用户组", dataType = "Integer")
    private Long userGroup;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "String")
    private Date createTime;

    @ApiModelProperty(name = "lastLoginTime", value = "上一次登陆时间", dataType = "String")
    private Date lastLoginTime;

    @ApiModelProperty(name = "salt", value = "加密盐值", dataType = "String")
    private String salt;

    @ApiModelProperty(name = "status", value = "用户状态", dataType = "Integer")
    private Integer status;

    @ApiModelProperty(name = "isOnline", value = "是否在线", dataType = "String")
    private String isOnline;

    @ApiModelProperty(name = "isLimit", value = "是否受限", dataType = "String")
    private String isLimit;

}
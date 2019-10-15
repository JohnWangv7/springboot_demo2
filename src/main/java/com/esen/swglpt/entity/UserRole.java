package com.esen.swglpt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRole implements Serializable {
    private Long userRoleId;

    private Long userId;

    private Long roleId;

}
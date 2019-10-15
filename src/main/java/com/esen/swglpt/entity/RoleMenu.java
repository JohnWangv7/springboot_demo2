package com.esen.swglpt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleMenu implements Serializable {
    private Long roleMenuId;

    private Long menuId;

    private Long roleId;
}
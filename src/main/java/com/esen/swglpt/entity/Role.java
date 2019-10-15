package com.esen.swglpt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Long roleId;

    private Long roleGroupId;

    private String roleName;

    private String roleDescription;

    private Short roleOrder;
}
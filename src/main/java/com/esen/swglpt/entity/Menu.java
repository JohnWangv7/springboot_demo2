package com.esen.swglpt.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Menu implements Serializable {
    private Long menuId;

    private String menuType;

    private String menuName;

    private String menuTag;

    private String menuUrl;

    private String menuDisabled;

    private Short menuOrder;

    private String menuDescription;

    private String perms;

    private String isMenu;
}
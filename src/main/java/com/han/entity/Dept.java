﻿package com.han.entity;

/**
 * 部门对象
 */
public class Dept {

    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Dept() {
    }

    public Dept(Integer id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }
}

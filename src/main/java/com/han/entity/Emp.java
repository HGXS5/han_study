package com.han.entity;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Emp {
    private Integer id;
    private String name;
    private Date birthday;

    public Emp(Integer id, String name, Date birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public Emp() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void SetBirthday(Date birthday) {
        this.birthday = birthday;
    }
}

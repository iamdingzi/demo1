package com.example.demo.entity;

/**
 * Created by Administrator on 2018/3/20/020.
 */
public class EmpVO extends Emp {

    private String dept;
    private String sort;
    private String location;

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

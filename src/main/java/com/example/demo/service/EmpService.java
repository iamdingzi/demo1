package com.example.demo.service;

import com.example.demo.entity.Emp;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20/020.
 */
public interface EmpService {

    List<Emp> getEmpByList();

    Emp getEmp(Emp param);

    Emp getEmpByName(String username);
}

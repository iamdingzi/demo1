package com.example.demo.controller;

import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@Controller
@RequestMapping("/register")
public class RegistorController {

    @Autowired
    private EmpService empService;

    @RequestMapping
    public String loginView(){
        return "register";
    }

    @ResponseBody
    @RequestMapping("list")
    public Object getEmp(){
        return empService.getEmpByList();
    }
}

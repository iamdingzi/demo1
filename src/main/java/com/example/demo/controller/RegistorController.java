package com.example.demo.controller;

import com.example.demo.entity.EmpVO;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@Controller
@RequestMapping("/register")
public class RegistorController {

    @Autowired
    private EmpService empService;

    @RequestMapping
    public String loginView() {
        return "register";
    }


    @RequestMapping("/reg")
    public Object register(EmpVO empVO) {
        String name = empVO.geteName();
        String pwd = empVO.getePwd();
        Integer deptId = empVO.geteDeptId();
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(pwd) || null == deptId) {
            throw new RuntimeException("信息错误！");
        }
        empService.save(empVO);
        return "/login";
    }
}

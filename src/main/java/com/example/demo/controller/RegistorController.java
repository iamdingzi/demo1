package com.example.demo.controller;

import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import com.example.demo.service.EmpService;
import com.example.demo.util.SwordBCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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


    /**
     * 注册账号
     * @param empVO
     * @return
     */
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

    @RequestMapping("/updateMima")
    public String updateMimaView() {
        return "/mima";
    }

    @RequestMapping("updatemm")
    @ResponseBody
    public Object updateMima(String oldpwd,Emp emp) {
        Emp param = new Emp();
        param.seteId(emp.geteId());
        Emp emp1 = empService.getEmp(param);
        if (!SwordBCrypt.checkpw(emp.geteName()+oldpwd, emp1.getePwd())) {
            return false;
        }
        emp.setePwd(SwordBCrypt.hashpw(emp.geteName()+emp.getePwd()));
        return empService.updatePwd(emp);
    }
}

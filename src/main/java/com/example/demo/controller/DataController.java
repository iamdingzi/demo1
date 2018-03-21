package com.example.demo.controller;

import com.example.demo.dao.DeptDao;
import com.example.demo.dao.EmpDao;
import com.example.demo.entity.Emp;
import com.example.demo.entity.EmpVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@RequestMapping("/data")
@Controller
public class DataController {

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private EmpDao empDao;

    @RequestMapping("/list")
    public String listView(Model model, EmpVO param, @RequestParam(defaultValue = "2") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageNumber) {
        List<String> locationList =  deptDao.getLocationList();
        List<Emp> emps = empDao.selectAll();
        List<String> hobbyList = emps.stream().map(Emp::geteHobby).collect(Collectors.toList());

        PageHelper.startPage(pageNumber,pageSize);
        List<EmpVO> list = empDao.selectList(param);
        PageInfo<EmpVO> pageInfo =new PageInfo<>(list);
        model.addAttribute("locationList", locationList);
        model.addAttribute("hobbyList", hobbyList);
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }
}

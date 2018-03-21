package com.example.demo.controller;

import com.example.demo.service.EmpService;
import com.example.demo.util.DateUtil;
import com.example.demo.util.ImageUtil;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/upload")
public class HeadUploadController {

    @Autowired
    EmpService empService;

    @RequestMapping
    public String loginView(){
        return "view";
    }

    @RequestMapping(value = "/editUserInfo")
    @ResponseBody
    public Object editUserInfo(HttpServletRequest request, String userId) {
        MultipartHttpServletRequest res = (MultipartHttpServletRequest)request;
        MultipartFile headpic = res.getFiles("file").get(0);
        empService.editUserInfo(headpic);
        return null;
    }
}

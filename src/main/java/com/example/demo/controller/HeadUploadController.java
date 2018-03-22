package com.example.demo.controller;

import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;


@Controller
@RequestMapping("/upload")
public class HeadUploadController {

    @Autowired
    EmpService empService;

    @RequestMapping
    public String loginView(){
        return "view";
    }

    /**
     * 上传头像
     * @param request
     * @param eId
     * @return
     */
    @RequestMapping(value = "/editUserInfo")
    @ResponseBody
    public Object editUserInfo(HttpServletRequest request, Integer eId) {
        MultipartHttpServletRequest res = (MultipartHttpServletRequest)request;
        MultipartFile headpic = res.getFiles("file").get(0);
        return empService.editUserInfo(headpic, eId);
    }


    /**
     * 图片地址
     * @param fileName
     * @param response
     */
    @RequestMapping("/img/{fileName}")
    public void imgSrc(@PathVariable  String fileName, HttpServletResponse response) {
        String filePath = "D:\\poho\\img\\"+fileName+".png";
        try {
            BufferedImage image = ImageIO.read(new File(filePath));
            OutputStream out = response.getOutputStream();
            ImageIO.write(image, "png", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

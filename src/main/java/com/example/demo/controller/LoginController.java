package com.example.demo.controller;

import com.example.demo.entity.Emp;
import com.example.demo.service.EmpService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.SwordBCrypt;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/20/020.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    private Producer captchaProducer = null;

    @Autowired
    public void setCaptchaProducer(Producer captchaProducer){
        this.captchaProducer = captchaProducer;
    }

    @Autowired
    private EmpService empService;

    @RequestMapping
    public String loginView(){
        return "login";
    }



    /**
     * 登录验证
     */
    @RequestMapping("/login")
    public String verification(String username, String userpassword, String code,
                               HttpServletRequest request, HttpServletResponse response, Model model) {

        Emp emp = empService.getEmpByName(username);

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(10*60*60);
        String sessionCode = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        model.addAttribute("username", username);
        if (StringUtils.isEmpty(code)||!code.toLowerCase().equals(sessionCode)){//忽略验证码大小写
            model.addAttribute("msg", "验证码错误");
            return "login";
        }
        if (emp==null) {
            model.addAttribute("msg", "用户不存在");
            return "login";
        }
        if (!SwordBCrypt.checkpw(username+userpassword, emp.getePwd())) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }

        session.setAttribute("loginUser", emp);
        session.setAttribute("nowTime", new Date());
        emp.setePwd(null);
        try {
            response.sendRedirect(request.getContextPath()+"/list");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value="/validateCode")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            Cookie uid = CookieUtil.getCookie(cookies, "uid");
            Cookie sid = CookieUtil.getCookie(cookies, "sid");
            if (uid!=null&&sid!=null) {
                uid.setMaxAge(0);
                sid.setMaxAge(0);
                uid.setPath("/");
                sid.setPath("/");
                response.addCookie(uid);
                response.addCookie(sid);
            }
        }
        request.getSession().setAttribute("loginUser", null);
        return "login";
    }
}

package com.baizhi.controller;


import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("code")
@Controller
public class SecurityController {

  @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取验证码
        String securityCode = SecurityCode.getSecurityCode();
        System.out.println(securityCode);
        //获取session作用域
        HttpSession session = request.getSession();
        //把验证码存入session作用域
        session.setAttribute("securityCode",securityCode);
        //获取图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        //设置响应类型
        response.setContentType("image/png");
        //响应输出流
        ImageIO.write(image,"png",response.getOutputStream());
    }
}

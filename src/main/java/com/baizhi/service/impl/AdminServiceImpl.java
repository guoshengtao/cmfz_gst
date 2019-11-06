package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public void login(Admin admin, String code, HttpServletRequest request) {

        //获取session作用域
        HttpSession session = request.getSession();
        //获取验证码
        String securityCode = (String) session.getAttribute("securityCode");
        //判断验证码是否正确
        if(!securityCode.equals(code)) throw new RuntimeException("验证码错误");
        //判断用户是否存在
        Admin admin1 = adminDao.selectOne(admin);
        if(admin1 == null) throw new RuntimeException("用户名或密码错误");
        session.setAttribute("admin1",admin1);

    }
}

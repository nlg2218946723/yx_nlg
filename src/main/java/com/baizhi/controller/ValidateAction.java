package com.baizhi.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/validate.png")
public class ValidateAction extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        String realCode = captcha.getCode();
        System.out.println("验证码：" + realCode);
        HttpSession session = req.getSession();
        session.setAttribute("realCode", realCode);
        OutputStream out = resp.getOutputStream();
        captcha.write(out);

    }
}

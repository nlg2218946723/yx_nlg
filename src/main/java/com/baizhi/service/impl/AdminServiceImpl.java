package com.baizhi.service.impl;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Resource
    HttpSession session;

    @Override
    public HashMap<String, Object> selectByUsername(Admin admin, String enCode) {
        String realCode = (String) session.getAttribute("realCode");
        Admin admin1 = adminMapper.selectByUsername(admin.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        if (realCode.equals(enCode)) {
            if (admin1 != null) {
                if (admin1.getPassword().equals(admin.getPassword())) {
                    session.setAttribute("admin", admin1);
                    map.put("massage", "登录成功");
                    map.put("status", 200);
                } else {
                    map.put("massage", "密码错误");
                    map.put("status", 201);
                }
            } else {

                map.put("massage", "账号不存在");
                map.put("status", 201);
            }
        } else {

            map.put("massage", "验证码错误");
            map.put("status", 201);
        }
        return map;
    }
}

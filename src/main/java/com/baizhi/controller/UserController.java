package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll(Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        //分页查询的结果
        List<User> list = userService.selectAllByPage(page, rows);
        //总条数
        Integer integer = userService.selectCounts();
        //总页数
        Integer totalPage = integer % rows == 0 ? integer / rows : (integer / rows) + 1;

        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("total", totalPage);
        map.put("records", integer);
        map.put("rows", list);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(User user, String oper) {
        System.out.println("user:" + user);
        if (oper.equals("add")) {

        }
        if (oper.equals("edit")) {
            userService.update(user);
        }
        if (oper.equals("del")) {

        }
        return "hello";
    }

    @RequestMapping("easyPoi")
    @ResponseBody
    public void easyPoi() throws IOException {
        //  System.out.println("----------------");
        List<User> list = userService.selectAll();
        //   System.out.println(list);
        ExportParams exportParams = new ExportParams("User表", "User表");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, User.class, list);
        workbook.write(new FileOutputStream(new File("E://User.xls")));
    }
}

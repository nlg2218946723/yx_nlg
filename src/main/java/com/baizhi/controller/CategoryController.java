package com.baizhi.controller;

import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("selectAllTwo")
    public void selectAllTwo(HttpServletResponse response) throws IOException {
        //   System.out.println("========进入查询二级类别==========");
        List<Category> categories = categoryService.selectAllTwo();
        StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        categories.forEach(category -> {
            sb.append("<option value=" + category.getId() + ">" + category.getCateName() + "</option>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().println(sb.toString());
    }


    @RequestMapping("selectAllLevelsOne")
    @ResponseBody
    public Map<String, Object> selectAllLevelsOne(Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
//        分页查询结果
        List<Category> categories = categoryService.selectAllLevelsOne(page, rows);
//        总条数
        Integer integer = categoryService.selectLevelsOneCounts();
//        总页数
        Integer totaPage = integer % rows == 0 ? integer / rows : (integer / rows) + 1;
        map.put("page", page);
        map.put("total", totaPage);
        map.put("rows", categories);
        map.put("records", integer);
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String, Object> edit(Category category, String oper) {//一级类别的增删改查和二级类别的增删改查都使用这个方法
        HashMap<String, Object> map = null;
        if (oper.equals("add")) {
            map = categoryService.insertOne(category);
        }
        if (oper.equals("edit")) {
            map = categoryService.updateOne(category);
        }
        if (oper.equals("del")) {
            map = categoryService.deleteOne(category.getId());
            System.out.println(map);
        }
        return map;
    }

    @RequestMapping("queryByTwoCategory")
    @ResponseBody
    public Map<String, Object> queryByTwoCategory(String cateId, Integer page, Integer rows, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
//        分页查询结果
        List<Category> list = categoryService.selectToTwo(cateId, page, rows);
//        当前类别总条数
        Integer integer = categoryService.selectToTwoCounts(cateId);
//        总页数
        Integer totaPage = integer % rows == 0 ? integer / rows : (integer / rows) + 1;
//        存进map集合
        map.put("page", page);
        map.put("total", totaPage);
        map.put("rows", list);
        map.put("records", integer);
        return map;
    }

}

package com.baizhi.controller;

import com.alibaba.fastjson.JSON;
import com.baizhi.entity.C;
import com.baizhi.entity.CityAndCount;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("echars")
public class EcharsController {

    @Resource
    UserService userService;

    public void getUserData() {
        System.out.println("进入goeasy");
        List<MonthAndCount> list = userService.selectMonth();
        List<String> month = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
//        遍历查询出来的月份和人数，并添加到相应的集合
        for (MonthAndCount monthAndCount : list) {
            month.add(monthAndCount.getMonths());
            num.add(monthAndCount.getCounts());
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("month", month);
        map.put("num", num);
        String jsonString = JSON.toJSONString(map);
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-2d098726607b42fbaf0e7cb536cedc4a");

        //发送消息   参数:通道名称,消息内容
        goEasy.publish("2005-channel", jsonString);
        System.out.println("发送成功");


    }

    @RequestMapping("getUserData")
    public HashMap<String, Object> getUserData2() {
        //  System.out.println("进入ajax");
        List<MonthAndCount> list = userService.selectMonth();
        List<String> month = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
//        遍历查询出来的月份和人数，并添加到相应的集合
        for (MonthAndCount monthAndCount : list) {
            month.add(monthAndCount.getMonths());
            num.add(monthAndCount.getCounts());
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("month", month);
        map.put("num", num);
        //   System.out.println("发送成功");
        return map;


    }

    @RequestMapping("city")
    @ResponseBody
    private ArrayList<C> city() {
        List<CityAndCount> list = userService.selectCity();
        System.out.println(list);
        ArrayList<C> citys = new ArrayList<>();
        citys.add(new C(list));
        return citys;
    }
}

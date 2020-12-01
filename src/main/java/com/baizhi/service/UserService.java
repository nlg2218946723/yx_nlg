package com.baizhi.service;

import com.baizhi.entity.CityAndCount;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;

import java.util.List;

public interface UserService {
    List<User> selectAllByPage(Integer pageNum, Integer pageSize);

    Integer selectCounts();

    void update(User user);

    List<User> selectAll();

    List<MonthAndCount> selectMonth();

    List<CityAndCount> selectCity();
}

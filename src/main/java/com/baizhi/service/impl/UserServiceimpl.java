package com.baizhi.service.impl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.entity.CityAndCount;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllByPage(Integer pageNum, Integer pageSize) {
        Integer i = (pageNum - 1) * pageSize;
        return userMapper.selectAllByPage(i, pageSize);
    }

    @Override
    public Integer selectCounts() {
        return userMapper.selectCounts();
    }

    @AddLog("修改用户状态")
    @DelCahe
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public List<MonthAndCount> selectMonth() {
        return userMapper.selectMonth();
    }

    @Override
    public List<CityAndCount> selectCity() {
        return userMapper.selectCity();
    }
}

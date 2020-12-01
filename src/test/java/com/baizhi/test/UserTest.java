package com.baizhi.test;

import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import com.baizhi.mapper.AdminMapper;
import com.baizhi.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> list = userMapper.selectAllByPage(0, 2);
        list.forEach(user -> System.out.println(user));
    }

    @Test
    public void test2() {
        List<MonthAndCount> monthAndCounts = userMapper.selectMonth();
        for (MonthAndCount monthAndCount : monthAndCounts) {
            System.out.println(monthAndCount);
        }
    }
}

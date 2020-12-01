package com.baizhi.mapper;

import com.baizhi.entity.CityAndCount;
import com.baizhi.entity.MonthAndCount;
import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAllByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    Integer selectCounts();

    void update(User user);

    List<User> selectAll();

    List<MonthAndCount> selectMonth();

    List<CityAndCount> selectCity();
}

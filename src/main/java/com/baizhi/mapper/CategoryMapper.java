package com.baizhi.mapper;

import com.baizhi.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    //查询全部一级类别
    List<Category> selectAllLevelsOne(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    //查询一级类别总条数
    Integer selectLevelsOneCounts();

    //添加一级类别
    void insertOne(Category category);

    //    修改一级类别
    void updateOne(Category category);

    //    删除一级类别
    void deleteOne(String id);

    //    查询一级类别下是否有二级类别
//    或者根据父类别id查询二级类别
    List<Category> selectOne(String id);

    //    查询二级类别  分页
    List<Category> selectToTwo(@Param("parentId") String parentId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    //    查询所有二级类别
    List<Category> selectAllTwo();

    //    查询指定二级类别总条数
    Integer selectToTwoCounts(String id);


}

package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CategoryService {
    //查询全部一级类别
    List<Category> selectAllLevelsOne(Integer pageNum, Integer pageSize);

    //    查询一级类别总条数
    Integer selectLevelsOneCounts();

    //    添加一级类别
    HashMap<String, Object> insertOne(Category category);

    //    修改一级类别
    HashMap<String, Object> updateOne(Category category);

    //    删除一级类别
    HashMap<String, Object> deleteOne(String id);

    //    查询二级类别  分页
    List<Category> selectToTwo(@Param("parentId") String parentId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    //    查询所有二级类别
    List<Category> selectAllTwo();

    //    查询指定二级类别总条数
    Integer selectToTwoCounts(String id);

    //    前台查询所有类别
    List<CategoryPO> queryAllCategory();

}

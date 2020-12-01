package com.baizhi.mapper;

import com.baizhi.po.CategoryPO;

import java.util.List;

public interface CategoryPOMapper {

    //    前台查询所有类别
    List<CategoryPO> queryAllCategory();
}

package com.baizhi.service.impl;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.entity.Category;
import com.baizhi.mapper.CategoryMapper;
import com.baizhi.mapper.CategoryPOMapper;
import com.baizhi.po.CategoryPO;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Resource
    CategoryPOMapper categoryPOMapper;

    @Override
    public List<Category> selectAllLevelsOne(Integer pageNum, Integer pageSize) {
        Integer i = (pageNum - 1) * pageSize;
        return categoryMapper.selectAllLevelsOne(i, pageSize);
    }

    @Override
    public Integer selectLevelsOneCounts() {
        return categoryMapper.selectLevelsOneCounts();
    }

    @AddLog("添加一二级类别")
    @DelCahe
    @Override
    public HashMap<String, Object> insertOne(Category category) {
        category.setId(UUID.randomUUID().toString());
        //如果category对象的父类别为空则levels赋值为1
        if (category.getParentId() == null) {
            category.setLevels(1);
        } else {//否则赋值为2
            category.setLevels(2);
        }

        categoryMapper.insertOne(category);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "修改成功");
        return map;
    }

    @AddLog("修改一二级类别")
    @DelCahe
    @Override
    public HashMap<String, Object> updateOne(Category category) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", "修改成功");
        categoryMapper.updateOne(category);
        return map;
    }

    @AddLog("删除一二级类别")
    @DelCahe
    @Override
    public HashMap<String, Object> deleteOne(String id) {
        HashMap<String, Object> map = new HashMap<>();
        String message = null;
        //根据父类id查询
        List<Category> categories = categoryMapper.selectOne(id);
        //如果长度为0则代表该类别下无子 类别
        if (categories.size() == 0) {
            //删除
            categoryMapper.deleteOne(id);
            message = "success";
            map.put("message", "删除成功");
            //否侧代表该类别下有子类别
        } else {
            map.put("message", "该类别下有子类别,不能删除");
            message = "error";
        }
        return map;
    }

    //查询二级类别
    @Override
    public List<Category> selectToTwo(String parentId, Integer pageNum, Integer pageSize) {
        Integer i = (pageNum - 1) * pageSize;
        return categoryMapper.selectToTwo(parentId, i, pageSize);
    }

    @Override
    public List<Category> selectAllTwo() {
        return categoryMapper.selectAllTwo();
    }

    //查询二级类别总条数
    @Override
    public Integer selectToTwoCounts(String id) {
        return categoryMapper.selectToTwoCounts(id);
    }

    @Override
    public List<CategoryPO> queryAllCategory() {
        return categoryPOMapper.queryAllCategory();
    }
}

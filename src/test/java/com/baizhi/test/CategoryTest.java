package com.baizhi.test;

import com.baizhi.entity.Category;
import com.baizhi.mapper.CategoryMapper;
import com.baizhi.mapper.CategoryPOMapper;
import com.baizhi.po.CategoryPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryTest {
    @Resource
    CategoryPOMapper categoryPOMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void test() {
        List<Category> categoryMappers = categoryMapper.selectAllLevelsOne(0, 1);
        categoryMappers.forEach(categoryMapper1 -> System.out.println(categoryMapper1));
    }

    @Test
    public void counts() {
        Integer integer = categoryMapper.selectLevelsOneCounts();
        System.out.println(integer);
    }

    @Test
    public void testWeb() {
        List<CategoryPO> categories = categoryPOMapper.queryAllCategory();
        for (CategoryPO category : categories) {
            System.out.println(category);
            List<Category> categoryList = category.getCategoryList();
            categoryList.forEach(category1 -> System.out.println(category1.getCateName()));
        }
    }
}

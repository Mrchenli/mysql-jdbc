package com.kiibos.mysqljdbc.service;

import com.kiibos.mysqljdbc.model.Category;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName CategoryServiceTest
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午11:19
 **/
public class CategoryServiceTest {

    private CategoryService categoryService;

    @Before
    public void before(){
        categoryService = new CategoryServiceImpl();
    }

    @Test
    public void testGetCategory(){
        int id = 1;
        Category category = categoryService.getCategory(id);
        System.out.println(category);
    }

    @Test
    public void testDeleteCategory(){
        int id = 1;
        categoryService.deleteCategory(id);
    }


}

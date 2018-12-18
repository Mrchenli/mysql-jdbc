package com.kiibos.mysqljdbc.service;

import com.kiibos.mysqljdbc.model.Category;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CategoryServiceTest {

    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceTest.class);

    @Before
    public void before(){
        logger.info("ssssss");
        log.info("");
        categoryService = new CategoryServiceImpl();
    }

    @Test
    public void testGetCategory(){
        int id = 3;
        Category category = categoryService.getCategory(id);
        System.out.println(category);
    }

    @Test
    public void testDeleteCategory(){
        int id = 1;
        categoryService.deleteCategory(id);
    }

}

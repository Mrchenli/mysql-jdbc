package com.kiibos.mysqljdbc.spring;

import com.kiibos.mysqljdbc.model.Category;
import com.kiibos.mysqljdbc.service.CategoryService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ClassName SpringQuickStart
 * @Description 测试spring容器AnnotationApplicationContext
 * @Author cl
 * @Date 2018/12/24 上午11:00
 **/
public class SpringQuickStart {

    /**
     * @Author kiibos
     * @Description // 这里可以联想到springboot的启动类 给一个配置类进去 带有@Configuration注解的类
     * @Date 上午11:24 2018/12/24
     * @param [args]
     * @return void
     **/
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CategoryService categoryService = context.getBean(CategoryService.class);
        int id = 3;
        Category category = categoryService.getCategory(id);
        System.out.println(category);
    }




}

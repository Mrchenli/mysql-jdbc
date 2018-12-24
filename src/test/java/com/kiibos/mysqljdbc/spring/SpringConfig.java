package com.kiibos.mysqljdbc.spring;

import com.kiibos.mysqljdbc.dao.CategoryMapper;
import com.kiibos.mysqljdbc.dao.MapperUtil;
import com.kiibos.mysqljdbc.dao.datasource.LxlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName SpringConfig
 * @Description 这个类的作用是spring容器的配置类 决定那些类给spring管理
 *              这个类上面的@Configuration注解 点击去可以看到它上面有@Component 将当前类给spring这个篮子管理
 * @Author cl
 * @Date 2018/12/24 上午11:04
 **/
@Configuration
@ComponentScan(basePackages = "com.kiibos.mysqljdbc.service")
//SPRING将扫描那个包下面包含@Componet @Controller @Service @Repository等注解的类给spring管理
public class SpringConfig {

    @Bean //@Bean会将LxlDataSource 交给spring 管理，当然也可以@Componet也可以
    // ，但是如果datasource是jar包方式映入进来的就需要用@Bean比如druid依赖怎么去它源码里面加@Component
    public DataSource dataSource(){
        String URL = "jdbc:mysql://m1.kiibos.com:3316/pdc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String DRIVER = "com.mysql.jdbc.Driver";
        String USER_NAME = "root";
        String PWD = "example";
        return LxlDataSource.getDataSource(URL,DRIVER,PWD,USER_NAME,10);
    }

    @Bean
    public CategoryMapper categoryMapper(){
        return MapperUtil.getMapper(CategoryMapper.class);
    }



}

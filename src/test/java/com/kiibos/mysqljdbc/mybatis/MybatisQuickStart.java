package com.kiibos.mysqljdbc.mybatis;

import com.kiibos.mysqljdbc.dao.CategoryMapper;
import com.kiibos.mysqljdbc.dao.datasource.LxlDataSource;
import com.kiibos.mysqljdbc.model.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MybatisQuickStart
 * @Description TODO  mybatis 事务管理机制有2种
 *  todo 一种是利用java.sql.Connection对象完成对事物的提交
 *  todo 一种是mybatis自身不会去实现事务机制，而是让程序的容器(JBOSS,WebLogic)来实现对事物的管理
 *  todo 当和spring一起的时候，spring提供了一个实现类SpringManagedTransaction
 *  todo Transaction接口：
 *
 * @Author cl
 * @Date 2018/12/19 下午2:57
 **/
public class MybatisQuickStart {

    private static final String URL = "jdbc:mysql://m1.kiibos.com:3316/pdc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PWD = "example";

    /**
     * @Author kiibos
     * @Description todo  用的datasource是 PooledDataSource
     * @Date 下午4:24 2018/12/19
     * @param
     * @return org.apache.ibatis.session.SqlSessionFactory
     **/
    public static SqlSessionFactory getSqlSessionFactoryFromXml() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    /**
     * @Author kiibos
     * @Description //TODO 用的datasource是LxlDataSource
     * @Date 下午4:24 2018/12/19
     * @param
     * @return org.apache.ibatis.session.SqlSessionFactory
     **/
    public static SqlSessionFactory getSqlSessionFactory(){
        DataSource dataSource = LxlDataSource.getDataSource(DRIVER,URL,PWD,USER_NAME,10);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment =
                new Environment("development",transactionFactory,dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(CategoryMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    public static SqlSession getSqlSession(SqlSessionFactory sqlSessionFactory){
        return sqlSessionFactory.openSession();
    }


    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactoryFromXml();
        SqlSession sqlSession = getSqlSession(sqlSessionFactory);
        try {
            //调用方式1
            //todo Category 表 delete 字段 由于添加的时候没有指定导致 数据库中值为null（不为false 也不为true）
            Category category = sqlSession.selectOne("com.kiibos.mysqljdbc.dao.CategoryMapper.queryCategory",3);
            System.out.println(category.toString());
            //调用方式2
            CategoryMapper categoryMapper = sqlSession.getMapper(CategoryMapper.class);
            Category category1 = categoryMapper.queryCategory(3);
            System.out.println(category1.toString());
        }finally {
            sqlSession.close();
        }
    }


}

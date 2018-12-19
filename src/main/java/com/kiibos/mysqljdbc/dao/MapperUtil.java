package com.kiibos.mysqljdbc.dao;

import com.kiibos.mysqljdbc.dao.datasource.LxlDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName MapperUtil
 * @Description TODO
 * @Author cl
 * @Date 2018/12/19 下午4:53
 **/
public class MapperUtil {

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

    public static <T> T getMapper(Class<T> mapperClass){
        return getSqlSessionFactory().openSession().getMapper(mapperClass);
    }

    public static <T> T getMapperFromXml(Class<T> mapperClass){
        return getSqlSessionFactory().openSession().getMapper(mapperClass);
    }

}

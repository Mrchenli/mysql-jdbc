package com.kiibos.mysqljdbc.dao;


import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

/**
 * @ClassName BaseDao
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午10:20
 **/
public class BaseDao {

    private final String URL = "jdbc:mysql://m1.kiibos.com:3316/pdc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER_NAME = "root";
    private final String PWD = "example";

    /**
     * @Author kiibos
     * @Description //获取mysql连接
     * @Date 上午11:38 2018/12/14
     * @param
     * @return java.sql.Connection
     **/
    public Connection getConnection(){
        try {
            Connection connection = null;
            Class.forName(DRIVER).newInstance();
            connection =  DriverManager.getConnection(URL,USER_NAME,PWD);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author kiibos
     * @Description 关闭资源
     * @Date 上午11:38 2018/12/14
     * @param closeable
     * @return void
     **/
    public void close(AutoCloseable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            closeable = null;
        }
    }


    public void close(Statement statement,Connection connection){
        close(statement);
        close(connection);
    }

    public void close(ResultSet resultSet, Statement statement, Connection connection){
        close(resultSet);
        close(statement);
        close(connection);
    }

}

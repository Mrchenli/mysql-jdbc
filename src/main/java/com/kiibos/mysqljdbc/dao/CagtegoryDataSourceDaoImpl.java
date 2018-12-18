package com.kiibos.mysqljdbc.dao;

import com.kiibos.mysqljdbc.dao.datasource.LxlDataSource;
import com.kiibos.mysqljdbc.model.Category;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CagtegoryDaoImpl
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午10:35
 **/
public class CagtegoryDataSourceDaoImpl implements CategoryDao {

    private final String URL = "jdbc:mysql://m1.kiibos.com:3316/pdc?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER_NAME = "root";
    private final String PWD = "example";

    LxlDataSource lxlDataSource = new LxlDataSource(DRIVER,URL,PWD,USER_NAME,10);

    @Override
    public List<Category> queryCategory(int id) {
        List<Category> categories = new ArrayList<>();
        Connection connection=null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            connection = lxlDataSource.getConnection();
            statement = connection.createStatement();
            String sql = "select * from category where id="+id;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCategory_name(resultSet.getString("category_name"));
                category.setCreate_time(resultSet.getDate("create_time"));
                categories.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(resultSet,statement,connection);
        }
        return categories;
    }

    @Override
    public List<Category> queryPreparedCategory(int id) {
        List<Category> categories = new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement =null;
        ResultSet resultSet =null;
        try {
            connection = lxlDataSource.getConnection();
            String sql = "select * from category where id=?";//可以防止sql注入
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                // 数据库里面查出来的
                //id=1 cretate_time=18:00
                //category.setid(1) category.setCreateTime(18:00)
                Category category = new Category();
                Class clazz = Category.class;
                Field[] fields = clazz.getDeclaredFields();
                for (Field field: fields){
                    System.out.println(field.getName());
                    field.setAccessible(true);
                    if(field.getType()==int.class){
                        field.set(category,resultSet.getInt(field.getName()));
                    }
                    if(field.getType()==String.class){
                        field.set(category,resultSet.getString(field.getName()));
                    }
                    if(field.getType()==Date.class){
                        field.set(category,resultSet.getDate(field.getName()));
                    }
                    field.setAccessible(false);
                }
                categories.add(category);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(resultSet,statement,connection);
        }
        return categories;
    }


    @Override
    public void delete(int id) {//jdbc事务机制
        Connection connection=null;
        PreparedStatement statement =null;
        try {
            connection = lxlDataSource.getConnection();
            connection.setAutoCommit(false);
            String sql = "delete from category where id =?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            close(statement,connection);
        }
    }



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

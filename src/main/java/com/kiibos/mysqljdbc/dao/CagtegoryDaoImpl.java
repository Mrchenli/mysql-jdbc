package com.kiibos.mysqljdbc.dao;

import com.kiibos.mysqljdbc.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CagtegoryDaoImpl
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午10:35
 **/
public class CagtegoryDaoImpl extends BaseDao implements CategoryDao {


    @Override
    public List<Category> queryCategory(int id) {
        List<Category> categories = new ArrayList<>();
        Connection connection=null;
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String sql = "select * from category where id="+id;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                category.setCreateTime(resultSet.getDate("create_time"));
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
            connection = getConnection();
            String sql = "select * from category where id=?";//可以防止sql注入
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setCategoryName(resultSet.getString("category_name"));
                category.setCreateTime(resultSet.getDate("create_time"));
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
            connection = getConnection();
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


}

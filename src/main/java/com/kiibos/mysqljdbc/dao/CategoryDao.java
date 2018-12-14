package com.kiibos.mysqljdbc.dao;

import com.kiibos.mysqljdbc.model.Category;

import java.util.List;

/**
 * @ClassName CategoryDao
 * @Description 产品品类
 * @Author cl
 * @Date 2018/12/14 上午10:20
 **/
public interface CategoryDao {


    /**
     * @Author kiibos
     * @Description //删除指定ip的产品品类
     * @Date  2018/12/14
     * @param id
     * @return void
     **/
    void delete(int id);

    /**
     * @Author kiibos
     * @Description 更加id查询产品品类
     * @Date 上午11:36 2018/12/14
     * @param id
     * @return java.util.List<com.kiibos.mysqljdbc.model.Category>
     **/
    List<Category> queryCategory(int id);
    /**
     * @Author kiibos
     * @Description 根据id查询产品品类
     * @Date 上午11:37 2018/12/14
     * @param id
     * @return java.util.List<com.kiibos.mysqljdbc.model.Category>
     **/
    List<Category> queryPreparedCategory(int id);

}

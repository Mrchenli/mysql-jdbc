package com.kiibos.mysqljdbc.service;

import com.kiibos.mysqljdbc.model.Category;

public interface CategoryService {

    /**
     * @Author kiibos
     * @Description //更加id获取产品品类信息
     * @Date 上午11:39 2018/12/14
     * @param id
     * @return com.kiibos.mysqljdbc.model.Category
     **/
    Category getCategory(Integer id);
    /**
     * @Author kiibos
     * @Description //根据id删除指定产品品类信息
     * @Date 上午11:39 2018/12/14
     * @param id
     * @return void
     **/
    void deleteCategory(Integer id);

}

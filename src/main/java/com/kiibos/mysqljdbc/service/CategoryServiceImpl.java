package com.kiibos.mysqljdbc.service;

import com.kiibos.mysqljdbc.dao.CagtegoryDaoImpl;
import com.kiibos.mysqljdbc.dao.CategoryDao;
import com.kiibos.mysqljdbc.model.Category;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午10:46
 **/
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CagtegoryDaoImpl();


    @Override
    public Category getCategory(Integer id) {
        return categoryDao.queryPreparedCategory(id).get(0);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryDao.delete(id);
    }

}
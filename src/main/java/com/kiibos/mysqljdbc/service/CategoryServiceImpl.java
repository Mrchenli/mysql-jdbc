package com.kiibos.mysqljdbc.service;

import com.kiibos.mysqljdbc.dao.*;
import com.kiibos.mysqljdbc.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Author cl
 * @Date 2018/12/14 上午10:46
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    //private CategoryDao categoryDao = new CagtegoryDataSourceDaoImpl();
    @Autowired
    private CategoryMapper categoryMapper;// = MapperUtil.getMapper(CategoryMapper.class);

    @Override
    public Category getCategory(Integer id) {
//        for (int i=0;i<=1024;i++){
//            categoryDao.queryPreparedCategory(id).get(0);
//        }
//        return categoryDao.queryPreparedCategory(id).get(0);
        return categoryMapper.queryCategory(id);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }

}

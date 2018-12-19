package com.kiibos.mysqljdbc.dao;


import com.kiibos.mysqljdbc.model.Category;

import java.util.List;

/**
* @Description:    产品品类接口
* @Author:         lxl
* @CreateDate:     2018/12/11 13:29
* @UpdateUser:     lxl
* @UpdateDate:     2018/12/11 13:29
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public interface CategoryMapper {

    int saveCategory(Category category);

    Category queryCategory(int id);

    int updateCategory(Category category);

    int deleteCategory(int id);

    List<Category> categoryList();

}

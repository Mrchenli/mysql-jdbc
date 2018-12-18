package com.kiibos.mysqljdbc.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @Description: 产品品类实体
 * @Author: lxl
 * @CreateDate: 2018/12/11 13:31
 * @UpdateUser: lxl
 * @UpdateDate: 2018/12/11 13:31
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Data
@ToString
public class Category {
    /**
     * 产品品类id
     */
    private int id;

    /**
     * 产品品类父id
     */
    private int parent_id;
    /**
     * 创建人id
     */
    private int creator_id;
    /**
     * 产品品类名称
     */
    private String category_name;
    /**
     * 修改人id
     */
    private int lastUpdator_id;
    /**
     * 创建时间
     */
    private Date create_time;
    /**
     * 修改时间
     */
    private Date last_update_time;
    /**
     * 等级
     */
    private int level;

    /**
     * 是否删除
     */
    private boolean delete;

}

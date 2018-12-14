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
    private int parentId;
    /**
     * 创建人id
     */
    private int creatorId;
    /**
     * 产品品类名称
     */
    private String categoryName;
    /**
     * 修改人id
     */
    private int lastUpdatorId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 等级
     */
    private int level;

    /**
     * 是否删除
     */
    private boolean delete;

}

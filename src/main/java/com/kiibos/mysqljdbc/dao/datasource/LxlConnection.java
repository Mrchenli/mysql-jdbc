package com.kiibos.mysqljdbc.dao.datasource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName LxlConnection
 * @Description TODO
 * @Author cl
 * @Date 2018/12/17 下午6:31
 **/
public class LxlConnection extends AbstractConnection{


    private LxlDataSource _ds;//datasource回收连接


    /**
     * @Author kiibos
     * @Description //初始化尽量放到构造函数里面
     *                如果属性太多了造成构造函数形参太多了 可以考虑试试builder设计模式
     * @Date 下午2:49 2018/12/18
     * @param connection
     * @param dataSource
     * @return
    **/
    public LxlConnection(Connection connection, LxlDataSource dataSource) {
        super(connection);
        this._ds = dataSource;
    }
    /**
     * @Author kiibos
     * @Description //重写connection的关闭资源的方法
     * @Date 下午2:51 2018/12/18
     * @param
     * @return void
     **/
    @Override
    public void close(){
        this._ds._pushConnection(this);
    }
}

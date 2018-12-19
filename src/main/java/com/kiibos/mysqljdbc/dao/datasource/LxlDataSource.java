package com.kiibos.mysqljdbc.dao.datasource;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LxlDataSource
 * @Description
 * @Author cl
 * @Date 2018/12/17 下午6:31
 **/
@Data
public class LxlDataSource extends AbstractDataSource {

    private LinkedList<LxlConnection> conns = new LinkedList<>();//数据库连接

    private final ReentrantLock lock = new ReentrantLock();//全局锁

    private int missConn = 0;//出现异常的时候会关闭连接 导致连接丢失

    @Setter(value = AccessLevel.PRIVATE)
    private boolean needInit = true;//是否需要初始化 setter方法是私有的

    /** 标记这个连接池是否已经关闭 */
    private boolean closed;


    @Override
    public Connection getConnection() throws SQLException {
        if(needInit){
            _init();
        }
        lock.lock();//只能一个线程执行lock.lock lock.unlock之间的代码
        try {
            LxlConnection conn = null;
            if(conns.isEmpty()){//如果连接池不为空
                if(missConn<1){//如果连接池是空的 并且也没有丢失连接
                    throw new SQLException("Too many connection!!!");
                }//如果是有丢失连接 就重新创建一个连接
                conn = _newConnection();
                missConn--;//丢失连接数量减少1
                return conn;
            }
            conn = conns.poll();//从连接池里面获取一个连接
            if(_beforeReturn(conn)){//检验这个连接是否有效 有效直接返回
                return conn;
            }else{//无效
                try {
                    return _newConnection();//重新建立一个连接
                }catch (Throwable e){//如果建立连接失败了 就将丢失连接的数量+1
                    missConn++;
                    lock.unlock();//释放锁资源
                    throw new RuntimeException(e);
                }
            }
        }finally {
            lock.unlock();//释放锁资源
        }
    }


    //配置信息
    private String driverClassName;//mysql驱动
    private String url;//mysql服务器url
    private String password;//密码
    private String username;//用户名
    private int size = 10;//数据库连接池的大小

    /** 默认的AutoCommit设置 */
    private boolean defaultAutoCommit = false;
    /** 默认的事务级别 */
    private int defaultTransactionIsolation = Connection.TRANSACTION_READ_COMMITTED;

    /** 返回Conntion之前需要执行的SQL语句 */
    private String validationQuery;
    /** 校验连接是否有效的超时设置,仅当validationQuery为null时有效 */
    private int validationQueryTimeout = 1;



    private static LxlDataSource lxlDataSource;

    private LxlDataSource(String driverClassName, String url, String password, String username, int size) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.password = password;
        this.username = username;
        this.size = size;
    }
    /**
     * @Author kiibos
     * @Description //双重校验锁的单利模式
     * @Date 上午10:14 2018/12/19
     * @param [driverClassName, url, password, username, size]
     * @return com.kiibos.mysqljdbc.dao.datasource.LxlDataSource
     **/
    public static LxlDataSource getDataSource(String driverClassName, String url, String password, String username, int size){
        if(lxlDataSource==null){
            synchronized (LxlDataSource.class){
                if(lxlDataSource==null){
                    lxlDataSource = new LxlDataSource(driverClassName,url,password,username,size);
                }
            }
        }
        return lxlDataSource;
    }

    /**
     * @Author kiibos
     * @Description //初始化数据库连接池
     * @Date 下午3:56 2018/12/18
     * @param
     * @return void
     **/
    protected void _init() throws SQLException{
        lock.lock();
        try {
            if(needInit){
                try {
                    Class.forName(driverClassName);
                } catch (ClassNotFoundException e) {
                    throw new SQLException(e);
                }
                conns = new LinkedList<>();
                for (int i=0;i<size;i++){
                    conns.push(_newConnection());
                }
                needInit = false;
            }
        }finally {
            lock.unlock();
        }
    }
    /**
     * @Author kiibos
     * @Description //回收连接到连接池
     *  这个方法用了lock是因为LinkedList不是一个并发集合工具类不是线程安全的所以需要lock
     * @Date 下午3:54 2018/12/18
     * @param conn
     * @return void
     **/
    protected void _pushConnection(LxlConnection conn){
        lock.lock();
        try {
            if(conns.size()<size){
                conns.push(conn);
            }else {
                _TrueClose(conn.get_con());
            }
        }finally {
            lock.unlock();
        }
    }

    /**
     * @Author kiibos
     * @Description //添加连接到连接池的时候设置连接的自动提交为false
     *  默认的事务隔离级别为读已提交
     * @Date 下午3:35 2018/12/18
     * @param conn
     * @return void
     **/
    protected void _beforePush(Connection conn) throws SQLException {
        conn.setAutoCommit(defaultAutoCommit);
        conn.setTransactionIsolation(defaultTransactionIsolation);
    }

    /**
     * @Author kiibos
     * @Description 验证连接是否有效果
     * @Date 下午3:37 2018/12/18
     * @param conn
     * @return boolean
     **/
    protected boolean _beforeReturn(LxlConnection conn) throws SQLException {
        Connection _conn = conn.get_con();
        if(validationQuery==null){
            return _conn.isValid(validationQueryTimeout);
        }else{
            try {
                _conn.createStatement().executeQuery(validationQuery);
            }catch (Throwable e){
                try {
                    if(!_conn.isClosed()){
                        _conn.close();
                    }
                }catch (SQLException e2){
                    e2.printStackTrace();
                }
                return false;
            }
            return true;
        }
    }
    /**
     * @Author kiibos
     * @Description 真正的从jdbc获取一个新连接
     * @Date 下午3:26 2018/12/18
     * @param
     * @return com.kiibos.mysqljdbc.dao.datasource.LxlConnection
     **/
    protected LxlConnection _newConnection() throws SQLException {
        if(closed){
            throw new SQLException("Datasource is closed!!!");
        }
        Connection _conn = null;

        try {
            _conn = DriverManager.getConnection(url,username,password);
            _beforePush(_conn);
            LxlConnection conn = new LxlConnection(_conn,this);
            return conn;
        }catch (Throwable e){
            _TrueClose(_conn);
            throw new RuntimeException(e);
        }
    }

    /**
     * @Author kiibos
     * @Description //关闭连接池
     * @Date 下午3:29 2018/12/18
     * @param
     * @return void
     **/
    public void close(){
        if(closed){
            return;
        }
        lock.lock();
        try {
            if(closed){
                return;
            }
            for (LxlConnection conn : conns){
                _TrueClose(conn.get_con());
            }
            conns.clear();
            closed = true;
        }finally {
            lock.unlock();
        }
    }

    /**
     * @Author kiibos
     * @Description //垃圾回收器会调用这个方法的
     *  当没人用这个连接池的时候gc会关闭掉这个数据库连接池
     * @Date 下午3:33 2018/12/18
     * @param
     * @return void
     **/
    @Override
    protected void finalize() throws Throwable {
        close();//关掉数据库连接池
        super.finalize();
    }

    /**
     * @Author kiibos
     * @Description //判断mysql连接是否真的关闭掉了
     * @Date 下午3:54 2018/12/18
     * @param _conn
     * @return void
     **/
    protected static void _TrueClose(Connection _conn) {
        try {
            if (_conn != null)
                if (!_conn.isClosed())
                    _conn.close();
        }
        catch (Throwable e) {}
    }
}

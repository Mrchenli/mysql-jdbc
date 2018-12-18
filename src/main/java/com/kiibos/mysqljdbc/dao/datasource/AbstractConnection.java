package com.kiibos.mysqljdbc.dao.datasource;

import lombok.Getter;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @ClassName AbstractConnection
 * @Description TODO
 * @Author cl
 * @Date 2018/12/17 下午7:11
 **/
public class AbstractConnection implements Connection {

    @Getter
    protected Connection _con;

    public AbstractConnection(Connection _con) {
        this._con = _con;
    }

    @Override
    public Statement createStatement() throws SQLException {
        return _con.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return _con.prepareStatement(sql);
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return _con.prepareCall(sql);
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return _con.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        _con.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return _con.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        _con.commit();
    }

    @Override
    public void rollback() throws SQLException {
        _con.rollback();
    }

    @Override
    public void close() throws SQLException {
        _con.close();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return _con.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return _con.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        _con.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return _con.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        _con.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return _con.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        _con.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return _con.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return _con.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        _con.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return _con.createStatement();
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return _con.prepareStatement(sql,resultSetType,resultSetConcurrency);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return _con.prepareCall(sql,resultSetType,resultSetConcurrency);
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return _con.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        _con.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        _con.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return _con.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return _con.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return _con.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        _con.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        _con.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return _con.createStatement(resultSetType,resultSetConcurrency,resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return _con.prepareStatement(sql,resultSetType,resultSetConcurrency,resultSetHoldability);
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return _con.prepareCall(sql,resultSetType,resultSetConcurrency,resultSetHoldability);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return _con.prepareStatement(sql,autoGeneratedKeys);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return _con.prepareStatement(sql,columnIndexes);
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return _con.prepareStatement(sql,columnNames);
    }

    @Override
    public Clob createClob() throws SQLException {
        return _con.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return _con.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return _con.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return _con.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return _con.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        _con.setClientInfo(name,value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        _con.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return _con.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return _con.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return _con.createArrayOf(typeName,elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return _con.createStruct(typeName,attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        _con.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return _con.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        _con.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        _con.setNetworkTimeout(executor,milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return _con.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return _con.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return _con.isWrapperFor(iface);
    }
}

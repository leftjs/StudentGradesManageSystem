package com.njupt.sms.utils;



import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Created by jason on 9/1/15.
 */
public class JdbcUtils {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "root";

    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/sms";

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public JdbcUtils() {
        try {
            Class.forName(DRIVER);
            System.out.println("数据库驱动加载成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库连接
     * @return
     */
    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 增加 删除 修改
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql,List<Object> params) throws SQLException {
        boolean flag = false;
        int result = -1;
        preparedStatement = connection.prepareStatement(sql);
        int index = 1;
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        result = preparedStatement.executeUpdate();
        flag = result > 0 ? true : false;
        return flag;
    }

    /**
     * 查询单条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
        Map<String, Object> map = new HashMap<String,Object>();
        int index = 1;
        preparedStatement = connection.prepareStatement(sql);
        if (params != null && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        resultSet = preparedStatement.executeQuery(); // 返回查询结果
        ResultSetMetaData metaData = resultSet.getMetaData();
        int col_len = metaData.getColumnCount();
        if (resultSet.next()){
            for(int i=0; i<col_len; i++ ){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
        }
        return map;
    }

    /**
     * 查询多条记录
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 1;
        preparedStatement = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        // System.out.println(metaData.getColumnName(6));
        int cols_len = metaData.getColumnCount();
        while(resultSet.next()){
            Map<String, Object> map = new HashMap<String, Object>();
            for(int i=0; i<cols_len; i++){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                map.put(cols_name, cols_value);
            }
            list.add(map);
        }

        return list;
    }

    /**通过反射机制查询单条记录
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> T findSimpleRefResult(String sql, List<Object> params,
                                     Class<T> cls )throws Exception{
        T resultObject = null;
        int index = 1;
        preparedStatement = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData  = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        if (resultSet.next()) {

            //通过反射机制创建一个实例
            resultObject = cls.newInstance();
            for (int i = 0; i < cols_len; i++) {
                String cols_name = metaData.getColumnName(i + 1);
                Object cols_value = resultSet.getObject(cols_name);
                if (cols_value == null) {
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                if (field.getType() == int.class) {
                    if (cols_value == "") {
                        cols_value = 0;
                    } else {
                        cols_value = Integer.parseInt(cols_value.toString());
                    }

                }
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
        }
        return resultObject;

    }

    /**通过反射机制查询多条记录
     * @param sql
     * @param params
     * @param cls
     * @return
     * @throws Exception
     */
    public <T> List<T> findMoreRefResult(String sql, List<Object> params,
                                         Class<T> cls )throws Exception {
        List<T> list = new ArrayList<T>();
        int index = 1;
        preparedStatement = connection.prepareStatement(sql);
        if(params != null && !params.isEmpty()){
            for(int i = 0; i<params.size(); i++){
                preparedStatement.setObject(index++, params.get(i));
            }
        }
        resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData  = resultSet.getMetaData();
        int cols_len = metaData.getColumnCount();
        while(resultSet.next()){
            //通过反射机制创建一个实例
            T resultObject = cls.newInstance();
            for(int i = 0; i<cols_len; i++){
                String cols_name = metaData.getColumnName(i+1);
                Object cols_value = resultSet.getObject(cols_name);
                if(cols_value == null){
                    cols_value = "";
                }
                Field field = cls.getDeclaredField(cols_name);
                if (field.getType() == int.class) {
                    if (cols_value == "") {
                        cols_value = 0;
                    } else {
                        cols_value = Integer.parseInt(cols_value.toString());
                    }

                }
                field.setAccessible(true); //打开javabean的访问权限
                field.set(resultObject, cols_value);
            }
            list.add(resultObject);
        }
        return list;
    }

    /**
     * 释放数据库连接
     */
    public void releaseConn(){

        System.out.println("数据库连接的销毁工作开始...");
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(SQLException e){
                e.printStackTrace();
            }finally {
                resultSet = null;
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                preparedStatement = null;
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                connection = null;
            }
        }


    }



}

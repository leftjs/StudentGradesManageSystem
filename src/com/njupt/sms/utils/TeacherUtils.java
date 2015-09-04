package com.njupt.sms.utils;

import com.njupt.sms.beans.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 9/2/15.
 */
public class TeacherUtils {

    private JdbcUtils jdbcUtils;

    public TeacherUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }


    public List<Map<String,Object>> findAllTeachers() {
        String sql = "select * from teacher order by id asc";
        List<Map<String, Object>> list = null;
        try {
            list = jdbcUtils.findModeResult(sql, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }

    public Teacher findTeacherById(int id) {
        String sql = "select * from teacher where id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);
        Teacher teacher = null;
        try {
            teacher = jdbcUtils.findSimpleRefResult(sql, params, Teacher.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacher;

    }

    public Map<String, Object> findTeacherMapById(int id) {
        String sql = "select * from teacher where id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);
        Map<String, Object> map = null;
        try {
            map = jdbcUtils.findSimpleResult(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean saveTeacher(Map<String, Object> map) {
        String sql = "";
        if (map.containsKey("id")) {
            sql = "update teacher set username = ?,password = ? ,name = ? ,phone = ? , email = ? where id = ?";
        } else {
            sql = "insert into teacher(username,password,name,phone,email) value(?,?,?,?,?)";
        }

        List<Object> params = new ArrayList<>();
        params.add(map.get("username"));
        params.add(map.get("password"));
        params.add(map.get("name"));
        params.add(map.get("phone"));
        params.add(map.get("email"));
        if (map.containsKey("id")) {
            params.add(map.get("id"));
        }
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean removeTeacher(int id) {
        String sql = "delete from teacher where id = ?";
        List<Object> params = new ArrayList<>();
        params.add(id);
        boolean flag = false;
        try {
            flag = jdbcUtils.updateByPreparedStatement(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (jdbcUtils != null) {
            jdbcUtils.releaseConn();
            jdbcUtils = null;

        }
        System.out.println(this.getClass().toString() + "销毁了");
    }
}

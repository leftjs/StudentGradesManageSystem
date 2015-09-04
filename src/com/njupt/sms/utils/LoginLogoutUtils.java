package com.njupt.sms.utils;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Admin;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 9/2/15.
 */
public class LoginLogoutUtils {

    private JdbcUtils jdbcUtils;

    public LoginLogoutUtils() {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }

    /**
     * 登陆
     * @param
     * @return
     */
    public String login(Object object) {

        String resultStr = "登陆失败";

        String username = null;
        String password = null;
        String sql = null;

        if (Admin.class == object.getClass()) {
            Admin admin = (Admin) object;
            username = admin.getUsername();
            password = admin.getPassword();
            sql = "select * from admin where username = ? and password = ?";
            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
               Admin databaseAdmin =  jdbcUtils.findSimpleRefResult(sql, params, Admin.class);
                if (databaseAdmin != null) {
                    Session.userInfo = databaseAdmin;
                    resultStr = "登陆成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (Student.class == object.getClass()) {
            Student student = (Student) object;
            username = student.getUsername();
            password = student.getPassword();
            sql = "select * from student where username = ? and password = ?";
            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
                Student databaseStudent = jdbcUtils.findSimpleRefResult(sql, params, Student.class);
                if (databaseStudent != null) {
                    Session.userInfo = databaseStudent;
                    resultStr = "登陆成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Teacher.class == object.getClass()) {
            Teacher teacher = (Teacher) object;
            username = teacher.getUsername();
            password = teacher.getPassword();
            sql = "select * from teacher where username = ? and password = ?";
            List<Object> params = new ArrayList<>();
            params.add(username);
            params.add(password);
            try {
                Teacher databaseTeacher = jdbcUtils.findSimpleRefResult(sql, params, Teacher.class);
                if (databaseTeacher != null) {
                    Session.userInfo = databaseTeacher;
                    resultStr = "登陆成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }

        return resultStr;

    }

    /**
     * 登出
     */
    public void logout() {
        Session.userInfo = null;
    }





    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (jdbcUtils != null) {
            jdbcUtils.releaseConn();
            jdbcUtils = null;

        }
        System.out.println(this.getClass().toString()+"销毁了");
    }
}

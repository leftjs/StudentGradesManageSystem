import com.njupt.sms.beans.Admin;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;
import com.njupt.sms.ui.HomeAdmin;
import com.njupt.sms.ui.HomeTeacher;
import com.njupt.sms.ui.Login;
import com.njupt.sms.utils.CourseUtils;
import com.njupt.sms.utils.JdbcUtils;
import com.njupt.sms.utils.LoginLogoutUtils;

import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        new Login();

//        new HomeTeacher();
//        CourseUtils courseUtils = new CourseUtils();
//        List<Map<String, Object>> list = courseUtils.findAllCourse();

//        JdbcUtils jdbcUtils = new JdbcUtils();
//        jdbcUtils.getConnection();
//
//        Date date = new Date();
//
//        String sql = "insert into student(username,password,studentCode,name,birthday) values(?,?,?,?,?)";
//        List<Object> params = new ArrayList<>();
          String username = "vineetit"
          System.out.println(username);
//        params.add("zhangsan");
//        params.add("123");
//        params.add("12001111");
//        params.add("xxx");
//        params.add(date);
//        try {
//            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
//            System.out.println(flag);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


}

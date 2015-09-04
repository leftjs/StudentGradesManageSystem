package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Course;
import com.njupt.sms.beans.Grade;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;
import com.njupt.sms.utils.CourseUtils;
import com.njupt.sms.utils.GradeUtils;
import com.njupt.sms.utils.TeacherUtils;
import com.njupt.sms.utils.UICommonUtils;
import org.w3c.dom.ls.LSInput;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 9/2/15.
 */
public class HomeTeacher {
    private JPanel homeTeacher;
    private JButton exitButton;
    private JTabbedPane tabbedPane1;
    private JTable infoTable;
    private JTable table2;
    private JTable table3;
    private JButton commitButton;
    private JButton saveToDraftButton;
    private JLabel welcomeNameLabel;
    private JFrame frame;
    private TeacherInfoModel teacherInfoModel;
    private CourseInfoModel courseInfoModel;
    private GradeInputModel gradeInputModel;


    public void makeRightAble() {
        saveToDraftButton.setEnabled(true);
        commitButton.setEnabled(true);
        table3.setEnabled(true);

    }

    public void makeRightDisable() {

        saveToDraftButton.setEnabled(false);
        commitButton.setEnabled(false);
        table3.setEnabled(false);
    }

    public HomeTeacher() {

        // 添加模型
        teacherInfoModel = new TeacherInfoModel();
        infoTable.setModel(teacherInfoModel);

        courseInfoModel = new CourseInfoModel();
        table2.setModel(courseInfoModel);



        gradeInputModel = new GradeInputModel();
        table3.setModel(gradeInputModel);




//        saveToDraftButton.setEnabled(false);
//        commitButton.setEnabled(false);
//        table3.setEnabled(false);

        frame = new JFrame("HomeTeacher");
        frame.setContentPane(homeTeacher);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();

        UICommonUtils.makeFrameToCenter(frame);
        frame.setVisible(true);


        Teacher teacher = (Teacher) Session.userInfo;
        welcomeNameLabel.setText(teacher.getName());


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
                Session.userInfo = null;
            }
        });


        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // System.out.println(table2.getSelectedRow());
                int courseId = Integer.parseInt(courseInfoModel.getValueAt(table2.getSelectedRow(), 0).toString().trim());
                boolean useDraft = !courseInfoModel.getValueAt(table2.getSelectedRow(), 4).toString().equals("已提交");
                if (useDraft) {
                    makeRightAble();
                } else {
                    makeRightDisable();

                }
                gradeInputModel.setStudentByCourseId(courseId, useDraft);

                super.mouseClicked(e);
            }
        });

        saveToDraftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gradeInputModel.saveToDraft();

                courseInfoModel.update();
            }
        });


        commitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                boolean flag = gradeInputModel.commitGrades();
                if (flag == true) {
                    makeRightDisable();
                    courseInfoModel.update();

                }
            }
        });
    }




    private class TeacherInfoModel extends AbstractTableModel {
        Teacher teacher = (Teacher) Session.userInfo;
        TeacherUtils teacherUtils = new TeacherUtils();
        Map<String, Object> map = teacherUtils.findTeacherMapById(teacher.getId());



        String[] columnStrings = {"id", "username", "password", "name", "phone", "email"};
        String[] columnShowStrings = {"编号","用户名","密码","姓名","电话","邮箱"};

        @Override

        public int getRowCount() {
            return 1;
        }

        @Override
        public int getColumnCount() {
            return map.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 2 || columnIndex == 4 || columnIndex == 5;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            super.setValueAt(aValue, rowIndex, columnIndex);
            map.put(columnStrings[columnIndex], aValue);
            save();
        }

        private void save() {
            teacherUtils.saveTeacher(map);
        }

        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }
    }

    private class CourseInfoModel extends AbstractTableModel {

        Teacher teacher = (Teacher) Session.userInfo;

        private CourseUtils courseUtils = new CourseUtils();
        private List<Map<String, Object>> list = getAllCourses();


        String[] columnStrings = {"id","courseName","academicYear","term","commitStatus"};
        String[] columnShowStrings = {"编号","课程名","所属学年","学期","提交状态"};


        public void update() {
            list = getAllCourses();
            fireTableDataChanged();

        }

        private List<Map<String,Object>> getAllCourses() {
            return  courseUtils.findAllCoursesByTeacherId(teacher.getId());
        }


        @Override
        public int getRowCount() {
            //System.out.println(list);
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);

            return map.get(columnStrings[columnIndex]);
        }



        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {


            return false;
        }

    }


    private class GradeInputModel extends AbstractTableModel {

        private int courseId;


        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        private CourseUtils courseUtils = new CourseUtils();
        private GradeUtils gradeUtils = new GradeUtils();
        private List<Map<String, Object>> list = new ArrayList<>();


        String[] columnStrings = {"id","studentCode","name","score","courseId"};
        String[] columnShowStrings = {"编号", "学号", "姓名", "成绩"};

        public List<Map<String, Object>> getAllStudentByCourseId(int courseId, boolean useDraft) {
            if (useDraft == true) {
                return courseUtils.findAllStudentWithGradeDraftByCourseId(courseId);
            } else {
                return courseUtils.findAllStudentWithGradeByCourseId(courseId);
            }

        }

        public boolean commitGrades() {



            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                if (map.get("score") == null || map.get("score") == "") {
                    JOptionPane.showMessageDialog(frame, "请将成绩填写完整后再提交", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                gradeUtils.saveGrade(map);
                courseUtils.commitCourseByCourseId(courseId);

            }
            return true;


        }

        /**
         * 调用来设置table中的显示数据
         *
         * @param courseId
         */
        public void setStudentByCourseId(int courseId, boolean useDraft) {

            this.courseId = courseId;

            list = getAllStudentByCourseId(courseId, useDraft);
            fireTableDataChanged();

        }

        public void saveToDraft() {
            for (int i = 0; i < list.size(); i++) {
                gradeUtils.saveGradeToDraft(list.get(i));
            }

            courseUtils.draftCourseByCourseId(courseId);
            setStudentByCourseId(courseId, true);


        }


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == columnShowStrings.length - 1;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(columnStrings[columnIndex], aValue);

        }



    }
}

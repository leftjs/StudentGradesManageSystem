package com.njupt.sms.ui;

import com.njupt.sms.Session;
import com.njupt.sms.beans.Admin;
import com.njupt.sms.beans.Course;
import com.njupt.sms.beans.Student;
import com.njupt.sms.beans.Teacher;
import com.njupt.sms.utils.*;
import com.sun.tools.corba.se.idl.constExpr.Times;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.sql.Savepoint;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by jason on 9/2/15.
 */
public class HomeAdmin {
    private JPanel homeAdmin;
    private JButton exitButton;
    private JLabel nameLabel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JTable table2;
    private JButton addOneRowCourseButton;
    private JButton deleteOneRowCourseButton;
    private JButton addOneRowTeacherButton1;
    private JButton deleteOneRowTeacherButton1;
    private JButton saveCourseButton;
    private JButton saveTeacherButton;
    private JTable table3;
    private JButton addOneStudentRowButton;
    private JButton saveStudentTableButton;
    private JButton deleteOneStudentRowButton;
    private JTable table4;
    private JButton cleanButton;
    private JTable table5;
    private JTable table6;
    private JButton statisticButton;
    private JTabbedPane tabbedPane2;
    private JTable course;
    private JTable studentClass;
    private JTable student;
    private JTable time;
    private JButton updateButton;
    private JComboBox comboBox1;
    private JFrame frame;

    private CourseModel courseModel;
    private TeacherModel teacherModel;

    private StudentInfoModel studentInfoModel;

    private ImportedGradeModel importedGradeModel;

    private CourseInfoModel courseInfoModel;
    private GradeInputModel gradeInputModel;

    private CourseStatisticModel courseStatisticModel;
                private TimeStatisticModel timeStatisticModel;
                private ClassStatisticModel classStatisticModel;
                private StudentStatisticModel studentStatisticModel;

    public static void main(String[] args) {

    }

    public HomeAdmin() {


        courseModel = new CourseModel();
        teacherModel = new TeacherModel();


        table1.setModel(courseModel);
        table2.setModel(teacherModel);


        studentInfoModel = new StudentInfoModel();
        table3.setModel(studentInfoModel);

        importedGradeModel = new ImportedGradeModel();
        table4.setModel(importedGradeModel);

        courseInfoModel = new CourseInfoModel();
        table5.setModel(courseInfoModel);

        gradeInputModel = new GradeInputModel();
        table6.setModel(gradeInputModel);

        courseStatisticModel = new CourseStatisticModel();
        timeStatisticModel = new TimeStatisticModel();
        classStatisticModel = new ClassStatisticModel();
        studentStatisticModel = new StudentStatisticModel();

//        private JTable course;
//        private JTable studentClass;
//        private JTable student;
//        private JTable time;
        course.setModel(courseStatisticModel);
        studentClass.setModel(classStatisticModel);
        time.setModel(timeStatisticModel);
        student.setModel(studentStatisticModel);


        Admin admin = (Admin) Session.userInfo;
        nameLabel.setText(admin.getUsername());


        frame = new JFrame("HomeAdmin");
        frame.setContentPane(homeAdmin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();

        UICommonUtils.makeFrameToCenter(frame);

        frame.setVisible(true);

        addOneRowCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(table2.getSelectedRow() > -1) || table2.getValueAt(table2.getSelectedRow(), 3) == null) {
                    JOptionPane.showMessageDialog(frame, "请在教师表中选择一项有教师姓名的行", "提示", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    Map<String, Object> map = new HashMap<String, Object>();
                    Object id = teacherModel.getValueAt(table2.getSelectedRow(), 0);
                    Object name = teacherModel.getValueAt(table2.getSelectedRow(), 3);
                    map.put("teacherId", id);
                    map.put("name", name);

                    courseModel.addRow(map);
                }

            }
        });


        addOneRowTeacherButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, Object> map = new HashMap<String, Object>();
                teacherModel.addRow(map);

            }
        });
        saveTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teacherModel.save();

            }
        });

        saveCourseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                courseModel.save();

            }
        });
        deleteOneRowCourseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() > -1) {
                    courseModel.remove(table1.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        deleteOneRowTeacherButton1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ((table2.getSelectedRow() > -1)) {
                    teacherModel.remove(table2.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        addOneStudentRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Map<String, Object> map = new HashMap<String, Object>();
                studentInfoModel.addRow(map);
            }
        });


        deleteOneStudentRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table3.getSelectedRow() > -1) {
                    studentInfoModel.removeStudentWithIndex(table3.getSelectedRow());

                } else {
                    JOptionPane.showMessageDialog(frame, "请选择一条记录", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        saveStudentTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentInfoModel.saveStudentTable();

            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                frame.dispose();
                Session.userInfo = null;
            }
        });

        cleanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table4.getSelectedRow() < 0) {
                    return;
                }
                int courseId = Integer.parseInt(importedGradeModel.getValueAt(table4.getSelectedRow(), 0).toString().trim());
                importedGradeModel.cleanCommitStatusByCourseId(courseId);

                courseInfoModel.update();
            }
        });
        table5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table5.getSelectedRow() < 0) {
                    return;
                }
                int courseId = Integer.parseInt(courseInfoModel.getValueAt(table5.getSelectedRow(), 0).toString().trim());
                gradeInputModel.setStudentByCourseId(courseId, false);
            }
        });




        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                private CourseStatisticModel courseStatisticModel;
//                private TimeStatisticModel timeStatisticModel;
//                private ClassStatisticModel classStatisticModel;
//                private StudentStatisticModel studentStatisticModel;

                courseStatisticModel.update();
                timeStatisticModel.update();
                classStatisticModel.update();
                studentStatisticModel.update();

            }
        });


        statisticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tabbedPane2.getSelectedIndex();
                if (index == 0) { // 按课程查询
                    if (course.getSelectedRow() < 0) {
                        return;
                    }
                    String courseName = courseStatisticModel.getValueAt(course.getSelectedRow(), 0).toString();
                    Map<String, Object> map = courseStatisticModel.getData(courseName);
                    makeChartByMap(map, "按课程(" + courseName + ")查询的统计图");

                }else if (index == 1) { // 按班级查询
                    if (studentClass.getSelectedRow() < 0) {
                        return;
                    }
                    int classCode = Integer.parseInt(classStatisticModel.getValueAt(studentClass.getSelectedRow(), 0).toString());
                    Map<String, Object> map = classStatisticModel.getData(classCode);
                    makeChartByMap(map, "按班级(" + classCode + "班)查询的统计图");

                }else if (index == 2) {
                    // 按学生查询 通过学号
                    if (student.getSelectedRow() < 0) {
                        return;
                    }
                    String studentCode = studentStatisticModel.getValueAt(student.getSelectedRow(), 0).toString();
                    Map<String, Object> map = studentStatisticModel.getData(studentCode);
                    makeChartByMap(map, "按学生(学号为:" + studentCode + ")查询的统计图");
                }else if (index == 3) {
                    // 按时间查询
                    if (time.getSelectedRow() < 0) {
                        return;
                    }
                    String academicQuery = timeStatisticModel.getValueAt(time.getSelectedRow(), 0).toString();
                    String termQuery = timeStatisticModel.getValueAt(time.getSelectedRow(), 1).toString();
                    Map<String, Object> map = timeStatisticModel.getData(academicQuery, termQuery);
                    makeChartByMap(map, "按学年学期(" + academicQuery + "学年," + termQuery + "学期)查询的统计图");
                }

            }
        });

    }

    public void makeChartByMap(Map<String, Object> map,String title) {
        DefaultPieDataset dpd = new DefaultPieDataset(); //建立一个默认的饼图
        System.out.println(map);

        dpd.setValue("优", Integer.parseInt(map.get("优").toString()));
        dpd.setValue("良", Integer.parseInt(map.get("良").toString()));
        dpd.setValue("中", Integer.parseInt(map.get("中").toString()));
        dpd.setValue("差", Integer.parseInt(map.get("差").toString()));
        dpd.setValue("不及格", Integer.parseInt(map.get("不及格").toString()));

        JFreeChart chart = ChartFactory.createPieChart(title, dpd, true, true, false);
        PiePlot piePlot = (PiePlot) chart.getPlot();
        piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator(("{0}:({2})"), NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
        //可以查具体的API文档,第一个参数是标题，第二个参数是一个数据集，第三个参数表示是否显示Legend，第四个参数表示是否显示提示，第五个参数表示图中是否存在URL
        ChartFrame chartFrame = new ChartFrame(title, chart);
        //chart要放在Java容器组件中，ChartFrame继承自java的Jframe类。该第一个参数的数据是放在窗口左上角的，不是正中间的标题。
        chartFrame.pack(); //以合适的大小展现图形
        chartFrame.setVisible(true);//图形是否可见
    }


    /**
     * 私有教师模型
     */
    private class TeacherModel extends AbstractTableModel {

        TeacherUtils teacherUtils = new TeacherUtils();
        List<Map<String, Object>> list = teacherUtils.findAllTeachers();

        String[] tableStrings = {"id", "username", "password", "name", "phone", "email"};
        String[] showStrings = {"编号", "用户名", "密码", "教师姓名", "电话号码", "电子邮箱"};


        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return tableStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(tableStrings[columnIndex]);
        }


        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        public void addRow(Map<String, Object> row) {
            list.add(row);
            fireTableDataChanged();
        }

        public void save() {
            for (int i = 0; i < list.size(); i++) {
                boolean flag = teacherUtils.saveTeacher(list.get(i));
            }
            // 更新当前数据源
            list = teacherUtils.findAllTeachers();
            fireTableDataChanged();

        }

        public void remove(int rowIndex) {
            Map<String, Object> map = list.get(rowIndex);
            if (map.containsKey("id")) {
                teacherUtils.removeTeacher(Integer.parseInt(map.get("id").toString()));
            }
            list.remove(rowIndex);
            fireTableDataChanged();

        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }


    }


    /**
     * 私有内部类
     */
    private class CourseModel extends AbstractTableModel {

        CourseUtils courseUtils = new CourseUtils();
        List<Map<String, Object>> list = courseUtils.findAllCourse();

        String[] tableStrings = {"id", "courseName", "academicYear", "term", "name", "teacherId"};
        String[] showStrings = {"编号", "课程名", "学年", "学期", "授课教师"};

        public void addRow(Map<String, Object> row) {
            list.add(row);
            fireTableDataChanged();
        }


        public void save() {

            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                courseUtils.saveCourse(map);
            }
            list = courseUtils.findAllCourse();
            fireTableDataChanged();

        }


        public void remove(int rowIndex) {
            Map<String, Object> map = list.get(rowIndex);
            if (map.containsKey("id")) {
                courseUtils.removeCourse(Integer.parseInt(map.get("id").toString()));
            }
            list.remove(rowIndex);
            fireTableDataChanged();

        }


        @Override
        public int getRowCount() {

            return list.size();
        }

        @Override
        public int getColumnCount() {
            return tableStrings.length - 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            return map.get(tableStrings[columnIndex]);

        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(tableStrings[columnIndex], aValue);
        }

        @Override
        public String getColumnName(int column) {
            return showStrings[column];
        }

    }

    private class StudentInfoModel extends AbstractTableModel {

        private StudentInfoUtils studentInfoUtils = new StudentInfoUtils();
        private List<Map<String, Object>> list = getAllStudentsInfo();

        public List<Map<String, Object>> getAllStudentsInfo() {
            return studentInfoUtils.getAllStudentsInfo();
        }

        String[] columnStrings = {"id", "username", "password", "studentCode", "name", "studentClass", "age", "sex", "birthday", "address", "phone", "email"};
        String[] columnShowStrings = {"编号", "用户名", "密码", "学号", "姓名", "班级", "年龄", "性别", "生日", "地址", "电话", "邮箱"};

        public void addRow(Map<String, Object> map) {
            list.add(map);
            fireTableDataChanged();

        }

        public void saveStudentTable() {

            for (int i = 0; i < list.size(); i++) {
                studentInfoUtils.saveStudentInfoByMap(list.get(i));
            }

            list = getAllStudentsInfo();
            fireTableDataChanged();

        }

        public void removeStudentWithIndex(int index) {
            Map<String, Object> map = list.get(index);
            if (map.containsKey("id")) {
                boolean flag = studentInfoUtils.deleteStudentById(Integer.parseInt(map.get("id").toString().trim()));
                if (flag == true) {
                    list.remove(index);
                }
            } else {
                list.remove(index);
            }
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
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
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Map<String, Object> map = list.get(rowIndex);
            map.put(columnStrings[columnIndex], aValue);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        @Override
        public String getColumnName(int column) {
            return columnShowStrings[column];
        }

    }

    private class ImportedGradeModel extends AbstractTableModel {

        private CourseUtils courseUtils = new CourseUtils();
        private List<Map<String, Object>> list = getAllCourseInfo();

        String[] columnStrings = {"id", "courseName", "academicYear", "term", "name", "commitStatus"};
        String[] columnShowingStrings = {"编号", "课程", "学年", "学期", "姓名", "提交状态"};

        public void updateTable() {
            list = getAllCourseInfo();
        }

        public void cleanCommitStatusByCourseId(int id) {
            courseUtils.clearCommitStautsByCourseId(id);

            updateTable();
            fireTableDataChanged();

        }

        private List<Map<String, Object>> getAllCourseInfo() {
            return courseUtils.findAllCourse();
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public int getColumnCount() {
            return columnShowingStrings.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            Map<String, Object> map = list.get(rowIndex);

            return map.get(columnStrings[columnIndex]);
        }

        @Override
        public String getColumnName(int column) {
            return columnShowingStrings[column];
        }


    }


    /**
     * 课程信息模型
     */
    private class CourseInfoModel extends AbstractTableModel {


        private CourseUtils courseUtils = new CourseUtils();
        private List<Map<String, Object>> list = getAllCourses();


        String[] columnStrings = {"id", "courseName", "academicYear", "term", "commitStatus"};
        String[] columnShowStrings = {"编号", "课程名", "所属学年", "学期", "提交状态"};


        public void update() {
            list = getAllCourses();
            fireTableDataChanged();

        }

        private List<Map<String, Object>> getAllCourses() {
            return courseUtils.findAllCourse();
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


    /**
     * 课程成绩
     */
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


        String[] columnStrings = {"id", "studentCode", "name", "score", "courseId"};
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

    private class CourseStatisticModel extends AbstractTableModel {


        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllCourses();
        String[] columnStrings = {"courseName"};
        String[] columnShowStrings = {"课程"};


        private Map<String,Object> getData(String courseName) {

            return statisticUtils.getCourseStatisticalByCourseName(courseName).get(0);
        }

        public void update() {
            list = statisticUtils.getAllCourses();
            fireTableDataChanged();

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
    }

    private class StudentStatisticModel extends AbstractTableModel {


        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllStudents();
        String[] columnStrings = {"studentCode", "name"};
        String[] columnShowStrings = {"学号", "姓名"};


        private Map<String,Object> getData(String studentCode) {

            return statisticUtils.getStudentStatisticalByStudentCode(studentCode).get(0);
        }

        public void update() {
            list = statisticUtils.getAllStudents();
            fireTableDataChanged();

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
    }

    private class ClassStatisticModel extends AbstractTableModel {


        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllClasses();
        String[] columnStrings = {"studentClass"};
        String[] columnShowStrings = {"班级"};


        private Map<String,Object> getData(int studentClass) {

            return statisticUtils.getClassStatisticalByClass(studentClass).get(0);
        }

        public void update() {
            list = statisticUtils.getAllClasses();
            fireTableDataChanged();

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
    }


    private class TimeStatisticModel extends AbstractTableModel {


        private StatisticUtils statisticUtils = new StatisticUtils();
        private List<Map<String, Object>> list = statisticUtils.getAllTimes();
        String[] columnStrings = {"academicYear", "term"};
        String[] columnShowStrings = {"学年", "学期"};

        private Map<String,Object> getData(String academicYear,String term) {

            return statisticUtils.getTimeStatisticalTime(academicYear, term).get(0);
        }

        public void update() {
            list = statisticUtils.getAllTimes();
            fireTableDataChanged();

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
    }

}

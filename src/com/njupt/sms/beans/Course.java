package com.njupt.sms.beans;

import java.util.Date;

/**
 * Created by jason on 9/2/15.
 */
public class Course {
    private int id;
    private String courseName;
    private String academicYear;
    private String term;

    private int teacherId;

    private String commitStatus;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", term='" + term + '\'' +
                ", teacherId=" + teacherId +
                ", commitStatus='" + commitStatus + '\'' +
                '}';
    }

    public String getCommitStatus() {
        return commitStatus;
    }

    public void setCommitStatus(String commitStatus) {
        this.commitStatus = commitStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }




    public Course() {
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }


}

package com.njupt.sms.beans;

import java.util.Date;

/**
 * Created by jason on 9/2/15.
 */
public class Grade {

    private int id;

    private int courseId;

    private int studentId;

    private int score;

    @Override
    public String toString() {
        return "Grade{" +
                "score=" + score +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", id=" + id +
                '}';
    }

    public Grade(int courseId, int studentId, int score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

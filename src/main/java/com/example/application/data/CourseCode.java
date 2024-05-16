package com.example.application.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "course_code")
@IdClass(CourseCodeId.class)
public class CourseCode {
    @Id
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "type")
    private CodeType type;

    private Date time;
    private int timeOffset;
    @Column(unique = true)
    private String attendanceCode;

    public CourseCode() {
    }

    public CourseCode(Course course, CodeType type, Date time, int timeOffset, String attendanceCode) {
        this.course = course;
        this.type = type;
        this.time = time;
        this.timeOffset = timeOffset;
        this.attendanceCode = attendanceCode;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTimeOffset() {
        return timeOffset;
    }

    public void setTimeOffset(int timeOffset) {
        this.timeOffset = timeOffset;
    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }
}

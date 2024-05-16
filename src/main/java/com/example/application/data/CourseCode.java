package com.example.application.data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    private LocalDateTime time;
    private int timeOffset;
    @Column(unique = true)
    private String attendanceCode;

    public CourseCode() {
    }

    public CourseCode(Course course, CodeType type, LocalDateTime time, int timeOffset, String attendanceCode) {
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
    
    public LocalDateTime getTime() {
        return time;
    }

    public CodeType getType() {
        return type;
    }

    public void setType(CodeType type) {
        this.type = type;
    }


    public void setTime(LocalDateTime time) {
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

    public boolean isBefore() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isBefore(time);
    }

    public boolean isAfter() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.isAfter(time);
    }

    public boolean isCheckIn() {
        return type.getName().equals("check-in");
    }

    public boolean isCheckOut() {
        return type.getName().equals("check-out");
    }
}

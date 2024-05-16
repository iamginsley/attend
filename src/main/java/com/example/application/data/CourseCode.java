package com.example.application.data;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_code")
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
    private String attendanceCode;

    public LocalDateTime getTime() {
        return time;
    }

    public CodeType getType() {
        return type;
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

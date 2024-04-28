package com.example.application.data;

import jakarta.persistence.*;

import java.util.Date;

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

    private Date time;
    private int timeOffset;
    private String attendanceCode;
}

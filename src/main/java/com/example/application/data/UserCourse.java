package com.example.application.data;

import jakarta.persistence.*;

@Entity
@Table(name = "user_course")
@IdClass(UserCourseId.class)
public class UserCourse {


    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "courseId")
    private ParentCourse course;

    private boolean exempt;

    public ParentCourse getCourse() {
        return course;
    }
}

package com.example.application.data;

import java.io.Serializable;

public class UserCourseId implements Serializable {

    private Integer user;
    private Integer course;

    public UserCourseId() {}

    public UserCourseId(Integer user, Integer course) {
        this.user = user;
        this.course = course;
    }


}
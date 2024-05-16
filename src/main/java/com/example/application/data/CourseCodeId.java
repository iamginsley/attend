package com.example.application.data;

import java.io.Serializable;

public class CourseCodeId implements Serializable {
    private Integer course;
    private Integer type;

    public CourseCodeId() {}

    public CourseCodeId( Integer course, Integer type) {
        this.course = course;
        this.type = type;
    }
}

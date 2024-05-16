package com.example.application.data;

import java.io.Serializable;
import java.util.Objects;

public class CourseCodeId implements Serializable {

    private Course course;
    private CodeType type;

    // no-arg constructor
    public CourseCodeId() {
    }

    public CourseCodeId(Course course, CodeType type) {
        this.course = course;
        this.type = type;
    }

    // getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCodeId that = (CourseCodeId) o;
        return Objects.equals(course, that.course) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, type);
    }
}
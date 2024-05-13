package com.example.application.data;

import java.io.Serializable;

public class CodeScanUnexpectedId implements Serializable {

    private Integer students;
    private Integer course;
    private Integer type;

    public CodeScanUnexpectedId() {}

    public CodeScanUnexpectedId(Integer students, Integer course, Integer type) {
        this.students = students;
        this.course = course;
        this.type = type;
    }
}

package com.example.application.data;

import java.io.Serializable;

public class CodeScanId implements Serializable {

    private Integer students;
    private Integer course;

    private Integer type;

    public CodeScanId() {}

    public CodeScanId(Integer students, Integer course, Integer type) {
        this.students = students;
        this.course = course;
        this.type = type;
    }


}

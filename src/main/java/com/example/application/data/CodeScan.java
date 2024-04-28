package com.example.application.data;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "code_scan")
public class CodeScan {
    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User students;

    @Id
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "type")
    private CodeType type;

    private Date time;
}

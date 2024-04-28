package com.example.application.data;

import jakarta.persistence.*;

@Entity
@Table(name = "code_scan_unexpected")
public class CodeScanUnexpected {
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

    @ManyToOne
    @JoinColumn(name = "reason")
    private AbsenceCategory reason;

    private String proofDocument;

    private boolean accepted;
}

package com.example.application.data;

import jakarta.persistence.*;

@Entity
@Table(name = "code_scan_unexpected")
@IdClass(CodeScanUnexpectedId.class)
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

    public User getStudents() {
        return students;
    }

    public Course getCourse() {
        return course;
    }

    public CodeType getType() {
        return type;
    }

    public void setStudents(User students) {
        this.students = students;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public void setReason(AbsenceCategory reason) {
        this.reason = reason;
    }

    public void setProofDocument(String proofDocument) {
        this.proofDocument = proofDocument;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

package com.example.application.data;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "code_scan")
@IdClass(CodeScanId.class)
public class CodeScan {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User students;

    @Id
    @ManyToOne
    @JoinColumn(name = "courseId", insertable = false, updatable = false)
    private Course course;

    @Id
    @ManyToOne
    @JoinColumn(name = "type", insertable = false, updatable = false)
    private CodeType type;

    private Date time;

    public CodeScan() {}


    public void setStudents(User students) {
        this.students = students;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setType(CodeType type) {
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getStudents() {
        return students;
    }

    public Course getCourse() {
        return course;
    }

    public CodeType getType() {
        return type;
    }

    public Date getTime() {
        return time;
    }

}

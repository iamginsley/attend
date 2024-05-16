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

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "courseId")
    private Integer courseId;

    @Column(name = "typeId")
    private Integer typeId;

    public CodeScan() {}

    public CodeScan(Integer userId, Integer courseId, Integer typeId, Date time) {
        this.userId = userId;
        this.courseId = courseId;
        this.typeId = typeId;
        this.time = time;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}

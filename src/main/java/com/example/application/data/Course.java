package com.example.application.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parentCourseId")
    private ParentCourse parentCourse;

    @OneToMany(mappedBy = "course")
    private List<CourseCode> courseCodes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParentCourse getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(ParentCourse parentCourse) {
        this.parentCourse = parentCourse;
    }

    public List<CourseCode> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCode(CourseCode courseCode) {
        courseCodes.add(courseCode);
    }
}

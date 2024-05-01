package com.example.application.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "parent_course")
public class ParentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "facultyId")
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "lecturerId")
    private User lecturer;

    @OneToMany(mappedBy = "parentCourse")
    private Set<Course> children;
}

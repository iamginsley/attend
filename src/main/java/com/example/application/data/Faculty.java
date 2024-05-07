package com.example.application.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double attendanceRate;

    @ManyToMany
    @JoinTable(
            name = "user_faculty",
            joinColumns = @JoinColumn(name = "facultyId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> students;

    public Faculty() {}
}

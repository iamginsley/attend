package com.example.application.data;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String studentNumber;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "user_faculty",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "facultyId"))
    private Set<Faculty> faculties;

    @OneToMany(mappedBy = "user")
    private Set<UserCourse> userCourses;
}

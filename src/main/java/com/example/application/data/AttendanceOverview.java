package com.example.application.data;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable // Indicates that the entity is mapped to an immutable database view
@Table(name = "attendance_overview") // Specify the name of the database view
public class AttendanceOverview {
    @Id
    @Column(name = "userId")
    private int userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userSurname")
    private String userSurname;

    @Column(name = "parentCourseId")
    private int parentCourseId;

    @Column(name = "parentCourseName")
    private String parentCourseName;

    @Column(name = "facultyId")
    private int facultyId;

    @Column(name = "facultyName")
    private String facultyName;

    @Column(name = "requiredAttendanceRate")
    private Double requiredAttendanceRate;

    @Column(name = "isExempt")
    private Boolean isExempt;

    @Column(name = "totalSessions")
    private int totalSessions;

    @Column(name = "attendedSessions")
    private int attendedSessions;

    @Column(name = "meetsAttendanceRequirements")
    private Boolean meetsAttendanceRequirements;
}

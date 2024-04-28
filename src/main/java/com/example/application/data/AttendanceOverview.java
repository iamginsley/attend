package com.example.application.data;

import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable // Indicates that the entity is mapped to an immutable database view
@Table(name = "attendance_overview") // Specify the name of the database view
public class AttendanceOverview {
    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userSurname")
    private String userSurname;

    @Column(name = "parentCourseId")
    private Long parentCourseId;

    @Column(name = "parentCourseName")
    private String parentCourseName;

    @Column(name = "facultyId")
    private Long facultyId;

    @Column(name = "facultyName")
    private String facultyName;

    @Column(name = "requiredAttendanceRate")
    private Double requiredAttendanceRate;

    @Column(name = "isExempt")
    private Boolean isExempt;

    @Column(name = "totalSessions")
    private Long totalSessions;

    @Column(name = "attendedSessions")
    private Long attendedSessions;

    @Column(name = "meetsAttendanceRequirements")
    private Boolean meetsAttendanceRequirements;
}

package com.example.application.views.admin.course;

import com.example.application.data.User;

import java.util.Set;

public class AdminCourse {
    protected int Id;
    protected String CourseName;
    protected User Teacher;
    protected Set<User> Students;

    public int getId() {
        return Id;
    }

}

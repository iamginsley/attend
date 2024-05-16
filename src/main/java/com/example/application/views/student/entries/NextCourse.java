package com.example.application.views.student.entries;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;

public class NextCourse extends UserViewEntry {

    public NextCourse(ParentCourseService parentCourseService, CourseService courseService) {
        super("Next Course", parentCourseService, courseService);
        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{"IT-Strategie"});
        this.addThirdRow(new String[]{"08:30 - 18:30", "07.05.2024"});
        this.addButtonLayout(new CustomButton[] {new CustomButton("View all")});
    }
}

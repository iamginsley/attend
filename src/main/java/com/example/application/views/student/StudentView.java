package com.example.application.views.student;

import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.MainLayout;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.student.entries.CheckedIn;
import com.example.application.views.student.entries.NextCourse;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@PageTitle("Student | Attend")
@Route(value = "student-view", layout = MainLayout.class)
public class StudentView extends UserView {

    public StudentView(ParentCourseService parentCourseService, CourseService courseService) {
        super(parentCourseService, courseService);
    }

    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new NextCourse(super.parentCourseService, super.courseService),
                new CheckedIn(super.parentCourseService, super.courseService)
        );

        return bodyLayout;
    }
}

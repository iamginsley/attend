package com.example.application.views.teacher;

import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.MainLayout;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.teacher.entries.CurrentCourse;
import com.example.application.views.teacher.entries.NextCourse;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@PageTitle("Teacher | Attend")
@Route(value = "teacher-view", layout = MainLayout.class)
public class TeacherView extends UserView {

    public TeacherView(ParentCourseService parentCourseService, CourseService courseService) {
        super(parentCourseService, courseService);
    }

    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new CurrentCourse(super.parentCourseService, super.courseService),
                new NextCourse(super.parentCourseService, super.courseService)
        );

        return bodyLayout;
    }


}

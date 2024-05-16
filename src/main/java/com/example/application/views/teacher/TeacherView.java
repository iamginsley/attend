package com.example.application.views.teacher;

import com.example.application.security.SecurityService;
import com.example.application.service.*;
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

    public TeacherView(ParentCourseService parentCourseService, CodeScanService codeScanService, SecurityService securityService, UserDetailsServiceImpl userDetailsService, UserCourseService userCourseService, CourseService courseService, CourseCodeService courseCodeService, UserService userService) {
        super(parentCourseService, codeScanService, securityService,userDetailsService,userCourseService,courseService, courseCodeService, userService);
    }

    @Override
    protected HorizontalLayout getUserViewBody() {

       // getCurrentCourse();

        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new CurrentCourse(super.parentCourseService, super.courseService, super.courseCodeService, super.codeScanService, userService),
                new NextCourse(super.parentCourseService, super.courseService, super.courseCodeService, super.codeScanService, userService)
        );

        return bodyLayout;
    }


}

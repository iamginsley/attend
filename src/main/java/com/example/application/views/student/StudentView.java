package com.example.application.views.student;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.data.User;
import com.example.application.security.SecurityService;
import com.example.application.service.CodeScanService;
import com.example.application.service.UserCourseService;
import com.example.application.service.UserDetailsServiceImpl;
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

import java.security.Security;
import java.util.List;

@PermitAll
@PageTitle("Student | Attend")
@Route(value = "student-view", layout = MainLayout.class)
public class StudentView extends UserView {

    private List<Course> userCourses;

    private User currentUser;

    public StudentView(ParentCourseService parentCourseService, CodeScanService codeScanService, SecurityService securityService, UserDetailsServiceImpl userDetailsService, UserCourseService userCourseService, CourseService courseService) {
        super(parentCourseService, codeScanService, securityService, userDetailsService, userCourseService, courseService);
    }


    private void getUserCourses() {
        var userName = securityService.getAuthenticatedUser().getUsername();

        this.currentUser = userDetailsService.getUserIdByEmail(userName);

        this.userCourses = userCourseService.getCoursesByUser(this.currentUser.getId());
    }

    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        getUserCourses();

        // TODO
        // bodyLayout.add(
        //        new NextCourse()
        //);

        for (Course c : this.userCourses) {
            bodyLayout.add(new CheckedIn(c, this.currentUser.getId(), this.codeScanService, courseService,parentCourseService));
        }

        return bodyLayout;
    }
}

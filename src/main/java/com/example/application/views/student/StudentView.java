package com.example.application.views.student;

import com.example.application.data.Course;
import com.example.application.data.User;
import com.example.application.security.SecurityService;
import com.example.application.service.*;
import com.example.application.views.MainLayout;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.student.entries.CheckedIn;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.List;

@PermitAll
@PageTitle("Student | Attend")
@Route(value = "student-view", layout = MainLayout.class)
public class StudentView extends UserView {

    private List<Course> userCourses;

    private User currentUser;

    public StudentView(ParentCourseService parentCourseService, CodeScanService codeScanService, SecurityService securityService, UserDetailsServiceImpl userDetailsService, UserCourseService userCourseService, CourseService courseService, CourseCodeService courseCodeService, UserService userService) {
        super(parentCourseService, codeScanService, securityService, userDetailsService, userCourseService, courseService, courseCodeService, userService);
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
            bodyLayout.add(new CheckedIn(c, this.currentUser.getId(), this.codeScanService, this.courseService, this.parentCourseService, this.courseCodeService, this.userService));
        }

        return bodyLayout;
    }
}

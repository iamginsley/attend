package com.example.application.views.student;

import com.example.application.data.ParentCourse;
import com.example.application.service.CodeScanService;
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

    private ParentCourse parentCourse;

    public StudentView(ParentCourseService parentCourseService, CodeScanService codeScanService) {
        super(parentCourseService, codeScanService);
    }


    private void getCourse() {
        this.parentCourse = parentCourseService.findCourseById(1).get();

        System.out.println(this.parentCourse.getName());
    }

    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        getCourse();

        bodyLayout.add(
                new NextCourse(),
                new CheckedIn(this.parentCourse,this.codeScanService)
        );

        return bodyLayout;
    }
}

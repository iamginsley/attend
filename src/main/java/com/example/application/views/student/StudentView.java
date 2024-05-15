package com.example.application.views.student;

import com.example.application.views.MainLayout;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.student.entries.CheckedIn;
import com.example.application.views.student.entries.NextCourse;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "student-view", layout = MainLayout.class)
public class StudentView extends UserView {
    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new NextCourse(),
                new CheckedIn()
        );

        return bodyLayout;
    }
}

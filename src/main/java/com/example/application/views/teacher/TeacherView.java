package com.example.application.views.teacher;

import com.example.application.views.MainLayout;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.teacher.entries.CurrentCourse;
import com.example.application.views.teacher.entries.NextCourse;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "teacher-view", layout = MainLayout.class)
public class TeacherView extends UserView {
    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new CurrentCourse(),
                new NextCourse()
        );

        return bodyLayout;
    }
}

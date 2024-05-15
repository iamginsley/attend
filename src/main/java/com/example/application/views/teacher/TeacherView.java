package com.example.application.views.teacher;

import com.example.application.service.ParentCourseService;
import com.example.application.views.MainLayout;
import com.example.application.views.components.CustomButton;
import com.example.application.views.abstracts.UserView;
import com.example.application.views.teacher.entries.CurrentCourse;
import com.example.application.views.teacher.entries.NextCourse;
import com.example.application.views.teacher.modal.ViewAllCoursesModal;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "teacher-view", layout = MainLayout.class)
public class TeacherView extends UserView {
    private final ParentCourseService parentCourseService;

    public TeacherView(ParentCourseService parentCourseService) {
        this.parentCourseService = parentCourseService;
    }

    @Override
    protected HorizontalLayout getUserViewBody() {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.add(
                new CurrentCourse(),
                new NextCourse(new CustomButton[] {openViewAllCoursesModalButton()})
        );

        return bodyLayout;
    }

    private CustomButton openViewAllCoursesModalButton() {
        CustomButton openModalButton = new CustomButton("View all");
        openModalButton.addClickListener(e -> {
            var courses = parentCourseService.findCourseByLecturer(2);
            ViewAllCoursesModal viewAllCoursesModal = new ViewAllCoursesModal(courses);
            viewAllCoursesModal.open();
        });
        return openModalButton;
    }
}

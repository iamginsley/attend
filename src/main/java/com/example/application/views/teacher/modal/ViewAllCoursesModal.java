package com.example.application.views.teacher.modal;
import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.ParentCourseService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCoursesModal extends Dialog {
    private final List<Course> courses = new ArrayList<>();

    public ViewAllCoursesModal(ParentCourseService parentCourseService, UserService userService) {
        List<ParentCourse> courses = parentCourseService.findCourseByLecturer(userService.getUserId());

        for (ParentCourse course : courses) {
            this.courses.addAll(course.getChildren());
        }

        setHeight("50%");
        setWidth("50%");

        for (Course course: this.courses) {
            System.out.println(course.getName());
        }

        setModalBody();
    }

    public void setModalBody() {
        Grid<Course> grid = new Grid<>(Course.class);
        grid.addClassName("teacher-modal-courses-grid");
        grid.setSizeFull();
        grid.setItems(this.courses);
        grid.removeAllColumns();
        grid.addColumn("name").setHeader("Course Name");
        grid.addColumn(course -> course.getParentCourse().getName()).setHeader("Parent Course");
        grid.addColumn(course -> course.getParentCourse().getLecturer().getFullName()).setHeader("Lecturer");
        grid.addColumn(course -> course.getParentCourse().getFaculty().getName()).setHeader("Faculty");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        this.add(grid);
    }
}
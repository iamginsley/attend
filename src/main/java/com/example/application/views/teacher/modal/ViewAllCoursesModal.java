package com.example.application.views.teacher.modal;
import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;

import java.util.ArrayList;
import java.util.List;

public class ViewAllCoursesModal extends Dialog {
    private final List<Course> courses = new ArrayList<>();

    public ViewAllCoursesModal(List<ParentCourse> courses) {
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
        grid.setColumns("name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        this.add(grid);
    }
}
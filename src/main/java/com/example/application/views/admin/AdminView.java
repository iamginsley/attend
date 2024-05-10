package com.example.application.views.admin;

import com.example.application.views.MainLayout;
import com.example.application.views.admin.course.AdminCourse;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Contacts | Vaadin CRM")
@Route(value = "admin-view", layout = MainLayout.class)
public class AdminView extends VerticalLayout {
    Grid<AdminCourse> grid = new Grid<>(AdminCourse.class);
    TextField filterText = new TextField();

    public AdminView() {
        addClassName("admin-view");
        setSizeFull();
        configureGrid();

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("admin-grid");
        grid.setSizeFull();
        //grid.setColumns("Id", "courseName", "teacher");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter Course");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addCourseButton = new CustomButton("Add Course");

        HorizontalLayout toolbar = new HorizontalLayout(addCourseButton, filterText);
        toolbar.addClassName("toolbar");
        toolbar.setWidthFull();
        toolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        return toolbar;
    }
}
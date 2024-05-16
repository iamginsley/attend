package com.example.application.views.admin;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.*;
import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.MainLayout;
import com.example.application.views.admin.course.AddCourseForm;
import com.example.application.views.admin.course.AddParentCourseForm;
import com.example.application.views.admin.course.AdminCourse;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

@PermitAll
@PageTitle("Admin | Attend")
@Route(value = "admin-view", layout = MainLayout.class)
public class AdminView extends VerticalLayout {
    private final ParentCourseService parentCourseService;
    private final CourseService courseService;
    private final CourseCodeService CourseCodeService;
    private final CodeTypeService codeTypeService;
    private final UserDetailsServiceImpl userDetailsService;

    Grid<Course> grid = new Grid<>(Course.class);
    TextField filterText = new TextField();
    AddCourseForm addCourseForm;
    AddParentCourseForm addParentCourseForm;

    public AdminView(ParentCourseService parentCourseService, CourseService courseService, CourseCodeService CourseCodeService,
                     CodeTypeService codeTypeService, UserDetailsServiceImpl userDetailsService) {
        this.parentCourseService = parentCourseService;
        this.courseService = courseService;
        this.CourseCodeService = CourseCodeService;
        this.codeTypeService = codeTypeService;
        this.userDetailsService = userDetailsService;


        addClassName("admin-view");
        setSizeFull();
        configureGrid();
        configureAddCourseForm();
        configureAddParentCourseForm();

        add(getToolbar(), getContent());
    }

    private void configureGrid() {
        grid.removeAllColumns();

        grid.addColumn(Course::getId).setHeader("ID");
        grid.addColumn(Course::getName).setHeader("Name");

        grid.addClassNames("admin-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        List<Course> courses = courseService.findAllCourses();
        grid.setItems(courses);
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter Course");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        //Add Course Button
        Button addCourseButton = new CustomButton("Add Course");
        addCourseButton.addClickListener(e -> addCourseForm.setVisible(!addCourseForm.isVisible()));

        //Add Course Button
        Button addParentCourseButton = new CustomButton("Add Parent Course");
        addParentCourseButton.addClickListener(e -> addParentCourseForm.setVisible(!addParentCourseForm.isVisible()));


        HorizontalLayout toolbar = new HorizontalLayout(addCourseButton,
                addParentCourseButton,
                filterText);
        toolbar.addClassName("toolbar");
        toolbar.setWidthFull();
        toolbar.setJustifyContentMode(JustifyContentMode.BETWEEN);
        return toolbar;
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid,
                addCourseForm,
                addParentCourseForm);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, addCourseForm);
        content.setFlexGrow(1, addParentCourseForm);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureAddCourseForm() {
        addCourseForm = new AddCourseForm(parentCourseService, courseService, CourseCodeService, codeTypeService);
        addCourseForm.setWidth("25em");
        addCourseForm.setVisible(false); // Initially hide the form
    }

    private void configureAddParentCourseForm() {
        addParentCourseForm = new AddParentCourseForm(parentCourseService, userDetailsService);
        addParentCourseForm.setWidth("25em");
        addParentCourseForm.setVisible(false); // Initially hide the form
    }

}
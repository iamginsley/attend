package com.example.application.views.admin.course;

import com.example.application.data.Faculty;
import com.example.application.data.User;
import com.example.application.data.ParentCourse;
import com.example.application.service.ParentCourseService;
import com.example.application.service.UserDetailsServiceImpl;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class AddParentCourseForm extends FormLayout {
    private final ParentCourseService parentCourseService;
    private final UserDetailsServiceImpl userDetailsService;

    private TextField courseName = new TextField("Course Name");
    private ComboBox<Faculty> faculty = new ComboBox<>("Faculty");
    private ComboBox<User> lecturer = new ComboBox<>("Lecturer");

    CustomButton save = new CustomButton("Save");
    CustomButton delete = new CustomButton("Delete");
    CustomButton close = new CustomButton("Cancel");

    private List<Faculty> faculties;
    private List<User> lecturers;

    public AddParentCourseForm(ParentCourseService parentCourseService, UserDetailsServiceImpl userDetailsService) {
        this.parentCourseService = parentCourseService;
        this.userDetailsService = userDetailsService;
        addClassName("add-parent-course-form");
        getFacultys();
        getLecturers();

        courseName.setRequired(true);
        faculty.setRequired(true);
        lecturer.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.add(courseName,
                faculty,
                lecturer,
                createButtonsLayout());

        //save Course
        save.addClickListener(e -> {
            if (courseName.getValue() != null && !courseName.getValue().isEmpty() && faculty.getValue() != null) {
                ParentCourse newCourse = new ParentCourse();
                newCourse.setName(courseName.getValue());
                newCourse.setFaculty(faculty.getValue());
                //newCourse.setLecturer(lecturer.getValue());
                parentCourseService.saveCourse(newCourse);
                Notification.show("New course saved successfully");
            } else {
                Notification.show("Please fill in all fields");
            }
        });

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete);
    }

    private void getFacultys() {
        //test parent course
        Faculty faculty1 = new Faculty();
        faculty1.setName("Faculty 1");
        Faculty faculty2 = new Faculty();
        faculty2.setName("Faculty 2");
        Faculty faculty3 = new Faculty();
        faculty3.setName("Faculty 3");
        //end test parent course

        this.faculties = List.of(faculty1, faculty2, faculty3); //Change this mit DB Access
        this.faculty.setItems(this.faculties);
        this.faculty.setItemLabelGenerator(Faculty::getName);
    }
    private void getLecturers() {
        this.lecturers = userDetailsService.findAllLecturers();
        this.lecturer.setItems(this.lecturers);
        this.lecturer.setItemLabelGenerator(User::getName);
    }

}

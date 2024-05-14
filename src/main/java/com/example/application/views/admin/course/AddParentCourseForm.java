package com.example.application.views.admin.course;

import com.example.application.data.Faculty;
import com.example.application.data.ParentCourse;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.util.List;

public class AddParentCourseForm extends FormLayout {

    private TextField courseName = new TextField("Course Name");
    private ComboBox<Faculty> faculty = new ComboBox<>("Faculty");

    CustomButton save = new CustomButton("Save");
    CustomButton delete = new CustomButton("Delete");
    CustomButton close = new CustomButton("Cancel");

    private List<Faculty> faculties;

    public AddParentCourseForm() {
        addClassName("add-parent-course-form");
        getFacultys();

        courseName.setRequired(true);
        faculty.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.add(courseName,
                faculty,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
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

}

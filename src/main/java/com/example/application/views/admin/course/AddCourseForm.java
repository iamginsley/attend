package com.example.application.views.admin.course;

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

public class AddCourseForm extends FormLayout {
    private ComboBox<ParentCourse> courseName = new ComboBox<>("Parent Course");
    private TextField teacher = new TextField("Teacher");
    private DatePicker startDate = new DatePicker("Start Date");
    private TimePicker startTime = new TimePicker("Start Time");
    private DatePicker endDate = new DatePicker("End Date");
    private TimePicker endTime = new TimePicker("End Time");

    CustomButton save = new CustomButton("Save");
    CustomButton delete = new CustomButton("Delete");
    CustomButton close = new CustomButton("Cancel");

    private List<ParentCourse> parentCourses;

    public AddCourseForm() {
        addClassName("add-course-form");
        getParentCourses();

        courseName.setRequired(true);
        teacher.setRequired(true);
        startDate.setRequired(true);
        startTime.setRequired(true);
        endDate.setRequired(true);
        endTime.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        this.add(courseName,
                teacher,
                startDate,
                startTime,
                endDate,
                endTime,
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

    private void getParentCourses() {
        //test parent course
        ParentCourse parentCourse = new ParentCourse();
        parentCourse.setName("Math");
        ParentCourse parentCourse1 = new ParentCourse();
        parentCourse1.setName("English");
        ParentCourse parentCourse2 = new ParentCourse();
        parentCourse2.setName("Science");
        //end test parent course

        this.parentCourses = List.of(parentCourse, parentCourse1, parentCourse2); //Change this mit DB Access
        this.courseName.setItems(this.parentCourses);
        this.courseName.setItemLabelGenerator(ParentCourse::getName);



    }


}

package com.example.application.views.admin.course;

import com.example.application.data.*;
import com.example.application.service.CodeTypeService;
import com.example.application.service.CourseCodeService;
import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AddCourseForm extends FormLayout {
    private final ParentCourseService parentCourseService;
    private final CourseService courseService;
    private final CourseCodeService courseCodeService;
    private final CodeTypeService codeTypeService;

    private TextField courseName = new TextField("Course Name");
    private ComboBox<ParentCourse> parentCourse = new ComboBox<>("Parent Course");
    private DatePicker startDate = new DatePicker("Start Date");
    private TimePicker startTime = new TimePicker("Start Time");
    private DatePicker endDate = new DatePicker("End Date");
    private TimePicker endTime = new TimePicker("End Time");
    private IntegerField offsetTime = new IntegerField("Offset Time (Minutes)");

    CustomButton save = new CustomButton("Save");
    CustomButton delete = new CustomButton("Delete");

    private List<ParentCourse> parentCourses;
    private List<User> lecturers;

    public AddCourseForm(ParentCourseService parentCourseService, CourseService courseService, CourseCodeService courseCodeService,
                         CodeTypeService codeTypeService) {
        this.parentCourseService = parentCourseService;
        this.courseService = courseService;
        this.courseCodeService = courseCodeService;
        this.codeTypeService = codeTypeService;

        addClassName("add-course-form");
        getParentCourses();
        //save Course
        onSave();

        courseName.setRequired(true);
        parentCourse.setRequired(true);
        startDate.setRequired(true);
        startTime.setRequired(true);
        endDate.setRequired(true);
        endTime.setRequired(true);
        offsetTime.setRequired(true);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        this.add(
                courseName,
                parentCourse,
                startDate,
                startTime,
                endDate,
                endTime,
                offsetTime,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);

        save.addClickShortcut(Key.ENTER);

        return new HorizontalLayout(save, delete);
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

        //this.parentCourses = List.of(parentCourse, parentCourse1, parentCourse2); //Change this mit DB Access
        this.parentCourses = parentCourseService.findAllCourses();
        this.parentCourse.setItems(this.parentCourses);
        this.parentCourse.setItemLabelGenerator(ParentCourse::getName);
    }

    private void getLecturers() {
        //test parent course
        User user1 = new User();
        user1.setName("Lecturer 1");
        User user2 = new User();
        user2.setName("Lecturer 2");
        User user3 = new User();
        user3.setName("Lecturer 3");
        //end test parent course

        this.lecturers = List.of(user1, user2, user3); //Change this mit DB Access
        //this.lecturer.setItems(this.lecturers);
        //this.lecturer.setItemLabelGenerator(User::getName);
    }

    private String generateCourseCode(int id) {
        //generate course code random
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int codeLength = 9;
        Random random = new Random();

        StringBuilder code = new StringBuilder(codeLength);
        for (int i = 0; i < codeLength; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }
        return code.toString();
    }

    private void onSave() {
        save.addClickListener(e -> {
            if (courseName.getValue() != null && parentCourse.getValue() != null && startDate.getValue() != null
                    && startTime.getValue() != null && endDate.getValue() != null && endTime.getValue() != null
                    && offsetTime.getValue() != null) {
            LocalDateTime startDateTime = LocalDateTime.of(startDate.getValue(), startTime.getValue());
            LocalDateTime endDateTime = LocalDateTime.of(endDate.getValue(), endTime.getValue());

            if (startDateTime.isBefore(endDateTime)) {
                Course newCourse = new Course();
                newCourse.setName(courseName.getValue());
                newCourse.setParentCourse(parentCourse.getValue());
                var createdCourse = courseService.save(newCourse);

                int id = createdCourse.getId();

                String CourseCode = generateCourseCode(id);

                CourseCode courseCodeCheckIn = new CourseCode();
                courseCodeCheckIn.setCourse(createdCourse);
                courseCodeCheckIn.setTime(startDateTime);
                courseCodeCheckIn.setAttendanceCode(CourseCode);
                courseCodeCheckIn.setTimeOffset(offsetTime.getValue());
                courseCodeCheckIn.setType(this.codeTypeService.getCodeTypeById(1));
                courseCodeService.save(courseCodeCheckIn);

                CourseCode courseCodeCheckOut = new CourseCode();
                courseCodeCheckOut.setCourse(createdCourse);
                courseCodeCheckOut.setTime(endDateTime);
                courseCodeCheckOut.setAttendanceCode(CourseCode);
                courseCodeCheckIn.setTimeOffset(offsetTime.getValue());
                courseCodeCheckOut.setType(this.codeTypeService.getCodeTypeById(2));
                courseCodeService.save(courseCodeCheckOut);

                Notification.show("New course saved successfully");

                courseName.clear();
                parentCourse.clear();
                startDate.clear();
                startTime.clear();
                endDate.clear();
                endTime.clear();
                offsetTime.clear();

            } else {
                Notification.show("Start date/time must be before end date/time");
            }
        } else{
            Notification.show("Please fill in all fields");
        }
    });

}
}

package com.example.application.views.teacher.entries;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.teacher.Modal.CourseModal;

public class CurrentCourse extends UserViewEntry {
    public CurrentCourse() {
        super("Current Course");
        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{"Software Engineering 2", "Total"});
        this.addThirdRow(new String[]{"08:30 - 18:30", "34"});
        addButtonLayout(createButtonWithDialog());
    }

    private CustomButton[] createButtonWithDialog() {
        //Testpurpose only - create new course
        //Parent Course
        ParentCourse parentCourse = new ParentCourse();
        parentCourse.setName("Modal Engineering");
        Course testCorse = new Course();
        testCorse.setParentCourse(parentCourse);
        //End Testpurpose
        CourseModal courseModal = new CourseModal(testCorse);
        CustomButton courseLinkButton = courseModal.getCourseLinkButton();
        return new CustomButton[] {courseLinkButton};
    }
}

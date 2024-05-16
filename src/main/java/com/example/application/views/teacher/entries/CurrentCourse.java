package com.example.application.views.teacher.entries;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.teacher.modal.CourseModal;

public class CurrentCourse extends UserViewEntry {

    private Course currentCourse;

    public CurrentCourse(ParentCourseService parentCourseService, CourseService courseService) {
        super("Current Course", parentCourseService, courseService);
        getCurrentCourse();
        if (this.currentCourse != null) {
            System.out.println("Current Course: " + currentCourse.getName());
            this.addFirstRow(new String[]{"Management Center Innsbruck"});
            this.addSecondRow(new String[]{currentCourse.getName(), "Total"});
            this.addThirdRow(new String[]{"08:30 - 18:30", "34"});
            this.addButtonLayout(createButtonWithDialog());
        }
    }

    private void getCurrentCourse() {
        this.currentCourse = super.courseService.getCurrentCourseByLecturerId(6);
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

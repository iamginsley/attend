package com.example.application.views.teacher.entries;

import com.example.application.data.Course;
import com.example.application.service.*;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.teacher.modal.CheckHandleModal;

public class CurrentCourse extends UserViewEntry {

    private Course currentCourse;

    public CurrentCourse(ParentCourseService parentCourseService, CourseService courseService, CourseCodeService courseCodeService, CodeScanService codeScanService, UserService userService) {
        super("Current Course", parentCourseService, courseService, courseCodeService, codeScanService, userService);

        getCurrentCourse();

        if (this.currentCourse != null) {
            System.out.println("Current Course: " + currentCourse.getName());

            this.addFirstRow(new String[]{currentCourse.getParentCourse().getFaculty().getName()});
            this.addSecondRow(new String[]{currentCourse.getName(), "Total"});

            String courseTime = super.courseService.getCourseTimeTime(this.currentCourse.getId(), "check-in") + " - " + super.courseService.getCourseTimeTime(this.currentCourse.getId(), "check-out");
            String courseSize = String.valueOf(super.courseService.getCourseSize(this.currentCourse.getId()));

            this.addThirdRow(new String[]{courseTime, courseSize});
            this.addButtonLayout(new CustomButton[] {openCheckInModal()});
        } else {
            this.addFirstRow(new String[]{"No current course"});
        }
    }

    private void getCurrentCourse() {
        this.currentCourse = super.courseService.getCurrentCourseByLecturerId(userService.getUserId());
    }

    private CustomButton openCheckInModal() {
        CustomButton openModalButton = new CustomButton("Check In");

        openModalButton.addClickListener(e -> {
            CheckHandleModal checkHandleModal = new CheckHandleModal(1, this.currentCourse, super.courseService, super.courseCodeService, super.codeScanService);
            checkHandleModal.open();
        });

        return openModalButton;
    }
}

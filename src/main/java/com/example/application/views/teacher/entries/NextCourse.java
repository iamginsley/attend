package com.example.application.views.teacher.entries;

import com.example.application.data.Course;
import com.example.application.service.*;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.teacher.modal.ViewAllCoursesModal;

public class NextCourse extends UserViewEntry {
    private Course nextCourse;

    public NextCourse(ParentCourseService parentCourseService, CourseService courseService, CourseCodeService courseCodeService, CodeScanService codeScanService, UserService userService) {
        super("Next Course", parentCourseService, courseService, courseCodeService, codeScanService, userService);

        getNextCourse();

        if (this.nextCourse != null) {
            String startDate = super.courseService.getCourseTimeDate(this.nextCourse.getId(), "check-in");
            this.addFirstRow(new String[]{nextCourse.getParentCourse().getFaculty().getName(), startDate});

            String startTime = super.courseService.getCourseTimeTime(this.nextCourse.getId(), "check-in");
            this.addSecondRow(new String[]{nextCourse.getName(),startTime});

            this.addThirdRow(new String[]{"FHHA | Raum 202"});
            this.addButtonLayout(new CustomButton[] {openViewAllCoursesModalButton()});
        } else {
            this.addFirstRow(new String[]{"No course planned"});
        }
    }

    private void getNextCourse() {
        this.nextCourse = super.courseService.getNextCourseByLecturerIdTest(6);
    }

    private CustomButton openViewAllCoursesModalButton() {
        CustomButton openModalButton = new CustomButton("View all");
        openModalButton.addClickListener(e -> {
            ViewAllCoursesModal viewAllCoursesModal = new ViewAllCoursesModal(super.parentCourseService, super.userService);
            viewAllCoursesModal.open();
        });
        return openModalButton;
    }
}

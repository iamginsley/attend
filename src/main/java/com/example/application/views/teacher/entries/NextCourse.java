package com.example.application.views.teacher.entries;

import com.example.application.data.Course;
import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.teacher.modal.ViewAllCoursesModal;

public class NextCourse extends UserViewEntry {
    private Course nextCourse;

    public NextCourse(ParentCourseService parentCourseService, CourseService courseService) {
        super("Next Course", parentCourseService, courseService);

        getNextCourse();

        if (this.nextCourse != null) {
            this.addFirstRow(new String[]{"Fachhochschule Hagenberg", "07.05.2024"});
            this.addSecondRow(new String[]{nextCourse.getName(),"08:30"});
            this.addThirdRow(new String[]{"FHHA | Raum 202"});
            this.addButtonLayout(new CustomButton[] {openViewAllCoursesModalButton()});
        }
    }

    private void getNextCourse() {
        this.nextCourse = super.courseService.getNextCourseByLecturerIdTest(6);
    }

    private CustomButton openViewAllCoursesModalButton() {
        CustomButton openModalButton = new CustomButton("View all");
        openModalButton.addClickListener(e -> {
            var courses = parentCourseService.findCourseByLecturer(2);
            ViewAllCoursesModal viewAllCoursesModal = new ViewAllCoursesModal(courses);
            viewAllCoursesModal.open();
        });
        return openModalButton;
    }
}

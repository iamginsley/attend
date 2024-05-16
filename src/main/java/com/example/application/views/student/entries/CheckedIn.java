package com.example.application.views.student.entries;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CodeScanService;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.student.Modal.CheckInModal;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CheckedIn extends UserViewEntry {

    private CodeScanService codeScanService;

    private Integer userId;
    private Course course;

    public CheckedIn(Course course, Integer userId, CodeScanService codeScanService) {
        super("Checked In");

        this.codeScanService = codeScanService;
        this.course = course;
        this.userId = userId;

        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{course.getName()});
        this.addThirdRow(new String[]{"08:30 - 18:30"});

        addPulsatingCircle();

        addButtonLayout(createButtonWithDialog());
    }

    private void addPulsatingCircle() {

        var t = codeScanService.getCodeScanByStudentAndUser(userId,course.getId());

        HorizontalLayout pulsatingCircle = new HorizontalLayout();
        pulsatingCircle.addClassNames("pulsating-circle", t.isPresent() ? "true" : "false");
        setTopRightCornerContent(pulsatingCircle);
    }

    private CustomButton[] createButtonWithDialog() {
        // now checkin the course.
        CheckInModal checkInModal = new CheckInModal(course, this.codeScanService, this.userId);
        CustomButton courseLinkButton = checkInModal.getCourseLinkButton();
        return new CustomButton[] {courseLinkButton};
    }
}

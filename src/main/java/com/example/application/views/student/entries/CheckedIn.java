package com.example.application.views.student.entries;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CodeScanService;
import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.example.application.views.student.Modal.checkInModal;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CheckedIn extends UserViewEntry {

    private CodeScanService codeScanService;

    private ParentCourse parentCourse;

    public CheckedIn(ParentCourse parentCourse, CodeScanService codeScanService) {
        super("Checked In");
        this.codeScanService = codeScanService;
        this.parentCourse = parentCourse;
        addPulsatingCircle();
        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{parentCourse.getName()});
        this.addThirdRow(new String[]{"08:30 - 18:30"});
        addButtonLayout(createButtonWithDialog());
    }

    private void addPulsatingCircle() {

        var t = codeScanService.getCodeScanByStudentAndUser(9,1);

        HorizontalLayout pulsatingCircle = new HorizontalLayout();
        pulsatingCircle.addClassNames("pulsating-circle", t.isPresent() ? "true" : "false");
        setTopRightCornerContent(pulsatingCircle);
    }

    private CustomButton[] createButtonWithDialog() {
        //Testpurpose only - create new course
        //Parent Course
        ParentCourse parentCourse = new ParentCourse();
        parentCourse.setName("Modal Engineering");
        Course testCorse = new Course();
        testCorse.setParentCourse(parentCourse);
        //End Testpurpose
        checkInModal checkInModal = new checkInModal(testCorse, this.codeScanService);
        CustomButton courseLinkButton = checkInModal.getCourseLinkButton();
        return new CustomButton[] {courseLinkButton};
    }
}

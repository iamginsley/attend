package com.example.application.views.teacher.modal;

import com.example.application.data.CodeScan;
import com.example.application.data.Course;
import com.example.application.data.CourseCode;
import com.example.application.service.CodeScanService;
import com.example.application.service.CourseCodeService;
import com.example.application.service.CourseService;
import com.example.application.utils.QRCodeGenerator;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@CssImport("./styles/course-modal-styles.css")
public class CheckHandleModal extends Dialog {

    private String courseName;
    private int totalStudents;
    private int absentStudents;
    private int checkedInStudents;
    private String attendancePercentage;
    private LocalDateTime time;

    private final Course course;
    private CourseCode courseCode;
    private final List<CodeScan> codeScan;
    private final CourseService courseService;
    private final CourseCodeService courseCodeService;
    private final QRCodeGenerator qrCodeGenerator;
    private final CodeScanService codeScanService;
    private String code;
    private int type;

    public CheckHandleModal(int type, Course currentCourse, CourseService courseService, CourseCodeService courseCodeService, CodeScanService codeScanService) {
        this.courseService = courseService;
        this.courseCodeService = courseCodeService;
        this.codeScanService = codeScanService;
        this.qrCodeGenerator = new QRCodeGenerator(currentCourse.getId(), type,200, 200);

        this.course = currentCourse;
        this.courseCode = courseCodeService.getCourseCodeByCourseIdAndType(currentCourse.getId(), type);
        this.codeScan = codeScanService.getAllCodeScans().stream().filter(codeScan -> codeScan.getCourse().getId() == this.course.getId() && codeScan.getType().getId() == type).toList();
        this.type = type;

        if (courseCode != null) {
            setData();

            setWidth("100%");
            setHeight("100%");

            setLayout();
        } else {
            Notification.show("No course code entry found for course: " + currentCourse.getName());
            this.close();
        }

    }

    private void setData(){
        this.courseName = this.course.getName();
        this.code = this.qrCodeGenerator.getCode();

        this.totalStudents = this.courseService.getCourseSize(this.course.getId());
        this.absentStudents = 0;
        this.checkedInStudents = this.codeScan.size();

        this.courseCodeService.updateCode(this.course.getId(), this.type, this.code);

        this.time = this.courseCode.getTime();

        // Calculate attendance
        double attendance = ((double) checkedInStudents / totalStudents) * 100;
        this.attendancePercentage = String.format("%.1f%%", attendance);

    }

    private void setLayout(){
        VerticalLayout container = new VerticalLayout();
        container.addClassName("course-modal-container");

        // QR Code and Time
        HorizontalLayout topRow = new HorizontalLayout();
        topRow.addClassName("qr-and-time-layout");
        Image qrCode = this.qrCodeGenerator.getQrCodeImage();

        VerticalLayout timeInfo = new VerticalLayout();
        timeInfo.addClassName("time-info");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Span timeSpan = new Span("Valid until: " + time.format(formatter));
        Span backupCode = new Span("Backup Code: " + this.code);

        timeInfo.add(timeSpan, backupCode);
        topRow.add(qrCode);
        topRow.add(timeInfo);
        topRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        container.add(topRow);

        // Attendance Info

        VerticalLayout attendance = new VerticalLayout(new Span("Attendance " + attendancePercentage));
        attendance.addClassName("attendance-info");
        attendance.setAlignItems(FlexComponent.Alignment.CENTER);
        // Student Details
        HorizontalLayout detailsRow = new HorizontalLayout();
        detailsRow.addClassName("stats-layout");
        VerticalLayout details = new VerticalLayout();
        details.addClassName("stats-details");
        details.add(new Span(this.totalStudents + " Total"), new Span(this.absentStudents + " Absent"), new Span(this.checkedInStudents + " Checked in"));
        detailsRow.add(attendance);
        detailsRow.add(details);
        container.add(detailsRow);

        this.add(container);
    }

}

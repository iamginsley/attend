package com.example.application.views.teacher.Modal;

import com.example.application.data.Course;
import com.example.application.data.CourseCode;
import com.example.application.data.ParentCourse;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


@CssImport("./styles/course-modal-styles.css")
public class CourseModal extends Dialog{
    private CustomButton courseLinkButton;
    private ParentCourse parentCourse;
    private Course course;
    private CourseCode courseCode;
    //DATA for Output
    private String code;
    private String courseName;
    private int totalStudents;
    private int absentStudents;
    private int checkedInStudents;
    private String attendancePercentage;
    private Date time;

    public CourseModal(Course course){
        this.course = course;
        setData();

        setWidth("50%");
        setHeight("50%");

        setLayout();
    }

    public CustomButton getCourseLinkButton(){
        return this.courseLinkButton;
    }

    private void setData(){
        this.courseLinkButton = new CustomButton("Course Link");
        courseLinkButton.addClickListener(event -> this.open());

        //TBD --> Datenermittlung
        this.parentCourse = this.course.getParentCourse();
        this.courseName = this.course.getName();
        this.code = "MNO345";
        this.totalStudents = 24;
        this.absentStudents = 3;
        this.checkedInStudents = totalStudents - absentStudents;
        //now plus 2 hours
        this.time = new Date(System.currentTimeMillis() + 7200000);

        // Calculate attendance
        double attendance = ((double) checkedInStudents / totalStudents) * 100;
        this.attendancePercentage = String.format("%.1f%%", attendance);

    }

    private void setLayout(){
        VerticalLayout container = new VerticalLayout();
        container.addClassName("course-modal-container");

        // Close Button
        CustomButton closeButton = new CustomButton("Close");
        closeButton.addClickListener(event -> this.close());
        container.add(closeButton);

        // QR Code and Time
        HorizontalLayout topRow = new HorizontalLayout();
        topRow.addClassName("qr-and-time-layout");
        Image qrCode = new QRCodeGenerator(code, 200, 200).getQrCodeImage();

        VerticalLayout timeInfo = new VerticalLayout();
        timeInfo.addClassName("time-info");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Span timeSpan = new Span("Valid until: " + formatter.format(time));
        long diffInMillies = Math.abs(time.getTime() - System.currentTimeMillis());
        long remainingTime = TimeUnit.SECONDS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        Span remainingSpan = new Span(remainingTime + " seconds left"); // Assuming a countdown timer

        timeInfo.add(timeSpan, remainingSpan);
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
        details.add(new Span("24 Total"), new Span("3 Absent"), new Span("21 Checked in"));
        detailsRow.add(attendance);
        detailsRow.add(details);
        container.add(detailsRow);

        this.add(container);
    }

}

package com.example.application.views.student.Modal;

import com.example.application.data.CodeScan;
import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CodeScanService;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Date;

@CssImport("./styles/course-modal-styles.css")
public class checkInModal extends Dialog{
    private CustomButton courseLinkButton;
    private ParentCourse parentCourse;
    private Course course;
    private String courseName;

    private CodeScanService codeScanService;

    public checkInModal(Course course, CodeScanService codeScanService){
        this.codeScanService = codeScanService;

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

    }

    private void setLayout(){
        VerticalLayout container = new VerticalLayout();
        container.addClassName("course-modal-container");

        CustomButton closeButton = new CustomButton("Close");
        closeButton.addClickListener(event -> this.close());
        container.add(closeButton);

        Span courseName = new Span(this.courseName);

        // Input field for code
        TextField codeInput = new TextField("Enter code");

        Button submitButton = new Button("Submit");

        submitButton.addClickListener(event -> {
            // Get the input from the text field
            String input = codeInput.getValue();


            codeScanService.insertCodeScan(9,1,1,new Date());

            this.close();

        });

        container.add(codeInput, submitButton);

        this.add(container);
    }

}

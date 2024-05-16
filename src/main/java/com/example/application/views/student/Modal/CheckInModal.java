package com.example.application.views.student.Modal;

import com.example.application.data.Course;
import com.example.application.data.ParentCourse;
import com.example.application.service.CodeScanService;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Date;

@CssImport("./styles/course-modal-styles.css")
public class CheckInModal extends Dialog{
    private CustomButton courseLinkButton;
    private ParentCourse parentCourse;
    private Course course;
    private String courseName;

    private CodeScanService codeScanService;

    private Integer userId;

    public CheckInModal(Course course, CodeScanService codeScanService, Integer userId){
        this.codeScanService = codeScanService;

        this.course = course;

        this.userId = userId;

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
           try {
               String input = codeInput.getValue();

               var presentCourseCode =  codeScanService.checkIfValidCode(course.getId(), input);

               if(presentCourseCode == null) {
                   Notification notification = Notification.show("Invalid Code");
                   notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
               } else {

                   var checkedIn = codeScanService.userAlreadyCheckedIn(course.getId(),userId,presentCourseCode.getId());

                   if(checkedIn) {
                       Notification notification = Notification.show("Already checked In");
                       notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                   } else {
                       codeScanService.insertCodeScan(userId, course.getId(), presentCourseCode.getId(),new Date());

                       Notification notification = Notification.show("Check in Done.");
                       notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

                       this.close();
                   }

               }
           } catch (Exception e) {
               e.printStackTrace();

               Notification notification = Notification.show("Error while check in.");
               notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
           }

        });

        container.add(codeInput, submitButton);

        this.add(container);
    }

}

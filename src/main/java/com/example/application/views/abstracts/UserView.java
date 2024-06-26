package com.example.application.views.abstracts;

import com.example.application.security.SecurityService;
import com.example.application.service.*;
import com.example.application.views.components.calendar.CalendarComponent;
import com.example.application.views.components.calendar.CalendarEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Component
public abstract class UserView extends VerticalLayout {
    protected HorizontalLayout layoutBody;
    protected HorizontalLayout userEntriesLayout;

    protected ParentCourseService parentCourseService;

    protected CodeScanService codeScanService;

    protected SecurityService securityService;

    protected UserDetailsServiceImpl userDetailsService;

    protected UserCourseService userCourseService;

    protected CourseService courseService;

    protected CourseCodeService courseCodeService;

    protected UserService userService;


    @Autowired
    public UserView(
            ParentCourseService parentCourseService,
            CodeScanService codeScanService,
            SecurityService securityService,
            UserDetailsServiceImpl userDetailsService,
            UserCourseService userCourseService,
            CourseService courseService,
            CourseCodeService courseCodeService,
            UserService userService) {
        this.parentCourseService = parentCourseService;
        this.codeScanService = codeScanService;
        this.securityService = securityService;
        this.userDetailsService = userDetailsService;
        this.userCourseService = userCourseService;
        this.courseService = courseService;
        this.courseCodeService = courseCodeService;
        this.userService = userService;

        this.addClassName("user-view");

        this.userEntriesLayout = this.getUserViewBody();
        this.userEntriesLayout.addClassName("user-view-body");

        this.layoutBody = new HorizontalLayout(this.userEntriesLayout, getUserCalendar());
        this.layoutBody.addClassName("user-view-layout");
        this.layoutBody.setSizeFull();
        this.layoutBody.setFlexGrow(1, this.userEntriesLayout);


        add(this.layoutBody);
    }

    protected abstract HorizontalLayout getUserViewBody();

    private List<CalendarEvent> generateCalendarEvents() {
        List<CalendarEvent> events = new ArrayList<>();

        events.add(new CalendarEvent(LocalDate.now(), LocalTime.of(9,30),LocalTime.of(13,45),"Test", false ));
        events.add(new CalendarEvent(LocalDate.now(), LocalTime.of(9,30),LocalTime.of(14,45),"Sepp", true ));
        events.add(new CalendarEvent(LocalDate.now(), LocalTime.of(9,30),LocalTime.of(12,45),"Toll", false ));
        events.add(new CalendarEvent(LocalDate.now(), LocalTime.of(9,30),LocalTime.of(11,23),"Ois Geht dahi", false ));
        events.add(new CalendarEvent(LocalDate.now(), LocalTime.of(9,30),LocalTime.of(11,23),"Ois Geht dahi", false ));
        return  events;
    }

    protected HorizontalLayout getUserCalendar() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("user-view-calendar");
        layout.add(new CalendarComponent(generateCalendarEvents()));
        return layout;
    }
}

package com.example.application.views.abstracts;

import com.example.application.service.CodeScanService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.components.calendar.CalendarComponent;
import com.example.application.views.components.calendar.CalendarEvent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public abstract class UserView extends VerticalLayout {
    protected HorizontalLayout layoutBody;
    protected HorizontalLayout userEntriesLayout;

    protected ParentCourseService parentCourseService;

    protected CodeScanService codeScanService;

    @Autowired
    public UserView(ParentCourseService parentCourseService, CodeScanService codeScanService) {
        this.addClassName("user-view");
        this.parentCourseService = parentCourseService;
        this.codeScanService = codeScanService;

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

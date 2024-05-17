package com.example.application.views.components.calendar;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

@JavaScript("https://code.jquery.com/jquery-3.7.1.min.js")
@JavaScript("https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js")
@CssImport("./styles/slick.css")
public class CalendarComponent extends VerticalLayout {

    public CalendarComponent(List<CalendarEvent> events) {

        this.addClassName("calendar-component-wrapper");

        for (CalendarEvent e : events) {
            // Create a new CalendarItem and add it to the layout
            CalendarItem item = new CalendarItem(e);
            this.add(item);
        }
    }
}
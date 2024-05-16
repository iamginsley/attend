package com.example.application.views.components.calendar;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

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
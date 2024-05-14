package com.example.application.views.components.calendar;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public  class CalendarItem extends VerticalLayout {

    private CalendarEvent event;

    public CalendarItem(CalendarEvent event) {

        // Container for date
        Div dateContainer = new Div();
        dateContainer.addClassNames("event-card date-card");

        Div day = new Div(String.valueOf(event.getDate().getDayOfMonth()));
        Div month = new Div(event.getDate().getMonth().name());
        day.addClassName("day");
        month.addClassName("month");

        dateContainer.add(day, month);

        Div eventInfo = new Div();

        eventInfo.addClassName("event-info");

        // Container for time details
        HorizontalLayout timeContainer = new HorizontalLayout();
        timeContainer.addClassName("time-container");

        Div checkInDiv = new Div(new Div(event.getCheckInTime().toString()), new Div("Check In"));
        Div checkOutDiv = new Div(new Div(event.getCheckOutTime().toString()), new Div("Check Out"));
        Div totalDiv = new Div(new Div(event.getTotalTime()), new Div("Total"));
        checkInDiv.addClassName("time-entry");
        checkOutDiv.addClassName("time-entry");
        totalDiv.addClassName("time-entry");

        timeContainer.add(checkInDiv, checkOutDiv, totalDiv);

        // Description
        Div description = new Div(event.getDescription());
        description.addClassName("description");

        eventInfo.add(timeContainer, description);

        // Add components to the entry
        add(dateContainer,eventInfo);
        addClassNames("flex ac", "w-full", "calendar-item");

        if(event.isHighlighted()) {
            addClassName("is-active");
        }
    }
}

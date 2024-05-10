package com.example.application.views.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;

public class CalendarComponent extends Div {
    public CalendarComponent() {
        add(new H1("User Calendar Placeholder"));
        addClassName("calendar-placeholder");
    }
}

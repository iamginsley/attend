package com.example.application.views.components;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class DashboardEntry extends VerticalLayout {
    public DashboardEntry(String headerText) {
        add(new H2(headerText));
        this.addClassName("dashboard-entry");
        add(getBody());
    }

    protected abstract HorizontalLayout getBody();
}

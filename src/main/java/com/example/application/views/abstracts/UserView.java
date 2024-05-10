package com.example.application.views.abstracts;

import com.example.application.views.components.CalendarComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public abstract class UserView extends VerticalLayout {
    protected HorizontalLayout layoutBody;
    protected HorizontalLayout userEntriesLayout;

    public UserView() {
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

    protected HorizontalLayout getUserCalendar() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("user-view-calendar");
        layout.add(new CalendarComponent());
        return layout;
    }
}

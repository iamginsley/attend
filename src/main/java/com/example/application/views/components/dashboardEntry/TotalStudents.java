package com.example.application.views.components.dashboardEntry;

import com.example.application.views.components.DashboardEntry;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class TotalStudents extends DashboardEntry {
    public TotalStudents () {
        super("Total Students");
    }

    @Override
    protected HorizontalLayout getBody() {
        return new HorizontalLayout(
                getTotalStudentCount()
        );
    }

    private H3 getTotalStudentCount() {
        return new H3("1,204");
    }
}

package com.example.application.views.list;

import com.example.application.views.MainLayout;
import com.example.application.views.components.dashboardEntry.TotalStudents;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
public class Dashboard extends VerticalLayout {
    public Dashboard() {
        this.addClassName("dashboard");
        add(
                getDashboardBody()
        );
    }

    private HorizontalLayout getDashboardBody()  {
        HorizontalLayout bodyLayout = new HorizontalLayout();

        bodyLayout.addClassName("dashboard-body");

        bodyLayout.add(
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents(),
                new TotalStudents()
        );

        return bodyLayout;
    }
}
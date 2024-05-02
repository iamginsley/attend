package com.example.application.views;

import com.example.application.views.list.Dashboard;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        Icon logoIcon = new Icon(VaadinIcon.CALENDAR);
        logoIcon.addClassName("app-header-icon");

        H1 logo = new H1("Attend");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        var header = new HorizontalLayout(logoIcon, logo);

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM, "app-header");

        addToNavbar(header);
    }

    private void createDrawer() {
        Icon dashboardIcon = new Icon(VaadinIcon.DASHBOARD);
        RouterLink dashboardLink = new RouterLink("Dashboard", Dashboard.class);
        HorizontalLayout dashboardLayout = new HorizontalLayout(dashboardIcon, dashboardLink);
        dashboardLayout.addClassName("app-drawer-item"); // Add this line

        Icon attendanceIcon = new Icon(VaadinIcon.CALENDAR);
        RouterLink attendanceLink = new RouterLink("Attendance", Dashboard.class);
        HorizontalLayout attendanceLayout = new HorizontalLayout(attendanceIcon, attendanceLink);
        attendanceLayout.addClassName("app-drawer-item"); // Add this line

        VerticalLayout drawer = new VerticalLayout(dashboardLayout, attendanceLayout);
        drawer.addClassName("app-drawer");

        addToDrawer(drawer);
    }
}

package com.example.application.views;

import com.example.application.views.list.Dashboard;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
    public MainLayout() {
        createHeader();
    }

    private void createHeader() {
        Image logo = new Image("images/header_logo.png", "Attend Logo");

        HorizontalLayout logoLayout = new HorizontalLayout(logo, introductionLayout());

        HorizontalLayout header = new HorizontalLayout(logoLayout, getProfileButton());

        header.setWidthFull();
        header.addClassNames("app-header");

        addToNavbar(header);
    }

    private Image getProfileButton() {
        Image avatar = new Image("images/profile_placeholder.png", "Avatar");
        avatar.addClassName("app-header-profile");

        return avatar;
    }

    private VerticalLayout introductionLayout() {
        H1 helloTitle = new H1("Hello,");
        H1 nameTitle = new H1("Max Mustermann");

        helloTitle.addClassName("hello-title");
        nameTitle.addClassName("name-title");

        VerticalLayout layout = new VerticalLayout(helloTitle, nameTitle);

        layout.addClassName("introduction-layout");

        return layout;
    }

    // possibly needed for admin view
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

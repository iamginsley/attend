package com.example.application.views;

import com.example.application.security.SecurityService;
import com.example.application.views.abstracts.UserView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@PermitAll
@Route("")
public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    @Autowired
    public MainLayout(SecurityService securityService) {
        this.securityService = securityService;
        createHeader();
        String User = this.securityService.getAuthenticatedUser().getUsername();
        //get User role
        Collection<? extends GrantedAuthority> roles = this.securityService.getAuthenticatedUser().getAuthorities();
        //get Role at index 0
        String role = roles.iterator().next().getAuthority();
        System.out.println("User: " + User + " Role: " + role);

        if (role.equals("admin") && VaadinSession.getCurrent().getAttribute("adminViewLoaded") == null) {
            UI.getCurrent().navigate("admin-view");
            UI.getCurrent().getPage().executeJs("setTimeout(function(){ location.reload(); }, 1000);");
            VaadinSession.getCurrent().setAttribute("adminViewLoaded", true);
        } else if (role.equals("student") && VaadinSession.getCurrent().getAttribute("studentViewLoaded") == null) {
            UI.getCurrent().navigate("student-view");
            UI.getCurrent().getPage().executeJs("setTimeout(function(){ location.reload(); }, 1000);");
            VaadinSession.getCurrent().setAttribute("studentViewLoaded", true);
        } else if (role.equals("lecturer") && VaadinSession.getCurrent().getAttribute("teacherViewLoaded") == null) {
            UI.getCurrent().navigate("teacher-view");
            UI.getCurrent().getPage().executeJs("setTimeout(function(){ location.reload(); }, 1000);");
            VaadinSession.getCurrent().setAttribute("teacherViewLoaded", true);
        }
    }

    private void createHeader() {
        Image logo = new Image("images/header_logo.png", "Attend Logo");

        HorizontalLayout logoLayout = new HorizontalLayout(logo, introductionLayout());
        logoLayout.addClassName("header-logo-container");

        HorizontalLayout header = new HorizontalLayout(logoLayout, getProfileButton());

        header.setWidthFull();
        header.addClassNames("app-header");

        addToNavbar(header);
    }

    private Image getProfileButton() {
        Image avatar = new Image("images/profile_placeholder.png", "Avatar");
        avatar.addClassName("app-header-profile");

        ContextMenu profileMenu = new ContextMenu(avatar);
        profileMenu.setOpenOnClick(true);
        profileMenu.addItem("Settings", event -> navigateToSettings());
        profileMenu.addItem("Logout", event -> performLogout());

        return avatar;
    }

    private void navigateToSettings() {
        // Navigate to the settings view
        System.out.println("Settings clicked");
    }

    private void performLogout() {
        securityService.logout();
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
        RouterLink dashboardLink = new RouterLink("Dashboard", UserView.class);
        HorizontalLayout dashboardLayout = new HorizontalLayout(dashboardIcon, dashboardLink);
        dashboardLayout.addClassName("app-drawer-item"); // Add this line

        Icon attendanceIcon = new Icon(VaadinIcon.CALENDAR);
        RouterLink attendanceLink = new RouterLink("Attendance", UserView.class);
        HorizontalLayout attendanceLayout = new HorizontalLayout(attendanceIcon, attendanceLink);
        attendanceLayout.addClassName("app-drawer-item"); // Add this line

        VerticalLayout drawer = new VerticalLayout(dashboardLayout, attendanceLayout);
        drawer.addClassName("app-drawer");

        addToDrawer(drawer);
    }
}
package com.example.application.views.abstracts;

import com.example.application.service.CourseService;
import com.example.application.service.ParentCourseService;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public abstract class UserViewEntry extends VerticalLayout {
    protected final CourseService courseService;
    protected final ParentCourseService parentCourseService;

    private final String headerText;

    protected HorizontalLayout topRightCorner = new HorizontalLayout();

    private final HorizontalLayout firstRow = new HorizontalLayout();
    private final HorizontalLayout secondRow = new HorizontalLayout();
    private final HorizontalLayout thirdRow = new HorizontalLayout();

    private final HorizontalLayout buttonLayout = new HorizontalLayout();

    public UserViewEntry(String headerText, ParentCourseService parentCourseService, CourseService courseService ) {
        this.headerText = headerText;
        this.courseService = courseService;
        this.parentCourseService = parentCourseService;

        this.addClassName("user-view-entry");

        VerticalLayout body = new VerticalLayout();
        body.addClassName("user-view-entry-body");

        if (firstRow != null) {
            body.add(firstRow);
        }

        if (secondRow != null) {
            body.add(secondRow);
        }

        if (thirdRow != null) {
            body.add(thirdRow);
        }

        if (buttonLayout != null) {
            body.add(buttonLayout);
        }

        add(getHeader(), body);
    }

    private HorizontalLayout getHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.setWidthFull();
        header.addClassName("user-view-entry-header");

        header.add(getIcon(), getHeaderText(), topRightCorner);

        return header;
    }

    private H1 getHeaderText() {
        H1 headerText = new H1(this.headerText);
        headerText.addClassName("user-view-entry-header-text");
        return headerText;
    }

    /**
     * Allows subclasses to define custom first row in the body
     */
    protected void addFirstRow(String[] values) {
        firstRow.addClassNames("user-view-entry-row", "first");
        for (String value : values) {
            firstRow.add(new H3(value));
        }
    }

    /**
     * Allows subclasses to define custom second row in the body
     */
    protected void addSecondRow(String[] values) {
        secondRow.addClassNames("user-view-entry-row", "second");
        for (String value : values) {
            secondRow.add(new H3(value));
        }
    }

    /**
     * Allows subclasses to define custom third row content in the body
     */
    protected void addThirdRow(String[] values) {
        thirdRow.addClassNames("user-view-entry-row", "third");
        for (String value : values) {
            thirdRow.add(new H3(value));
        }
    }

    protected void addButtonLayout(CustomButton[] buttons) {
        buttonLayout.addClassNames("user-view-entry-button-layout");
        for (CustomButton button : buttons) {
            buttonLayout.add(button);
        }
        add(buttonLayout);
    }

    private HorizontalLayout getIcon() {
        HorizontalLayout icon = new HorizontalLayout();
        icon.addClassName("user-view-entry-icon");
        return icon;
    }

    /**
     * Allows subclasses to define custom content in the top-right corner
     * @param content the layout/content to be added
     */
    public void setTopRightCornerContent(HorizontalLayout content) {
        this.topRightCorner.removeAll();
        content.addClassName("user-view-entry-top-right-corner");
        this.topRightCorner.add(content);
    }
}

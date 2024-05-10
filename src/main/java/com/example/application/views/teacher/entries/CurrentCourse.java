package com.example.application.views.teacher.entries;

import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CurrentCourse extends UserViewEntry {
    public CurrentCourse() {
        super("Current Course");
        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{"Software Engineering 2", "Total"});
        this.addThirdRow(new String[]{"08:30 - 18:30", "34"});
        this.addButtonLayout(new CustomButton[]{new CustomButton("Check in Code"), new CustomButton("Check out Code")});
    }

    private void addPulsatingCircle() {
        HorizontalLayout pulsatingCircle = new HorizontalLayout();
        pulsatingCircle.addClassNames("pulsating-circle", "false");
        setTopRightCornerContent(pulsatingCircle);
    }
}

package com.example.application.views.student.entries;

import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class CheckedIn extends UserViewEntry {
    public CheckedIn() {
        super("Checked In");
        addPulsatingCircle();
        this.addFirstRow(new String[]{"Management Center Innsbruck"});
        this.addSecondRow(new String[]{"Software Engineering 2"});
        this.addThirdRow(new String[]{"08:30 - 18:30"});
        this.addButtonLayout(new CustomButton[]{new CustomButton("Course Link")});
    }

    private void addPulsatingCircle() {
        HorizontalLayout pulsatingCircle = new HorizontalLayout();
        pulsatingCircle.addClassNames("pulsating-circle", "false");
        setTopRightCornerContent(pulsatingCircle);
    }
}

package com.example.application.views.teacher.entries;

import com.example.application.views.abstracts.UserViewEntry;
import com.example.application.views.components.CustomButton;

public class NextCourse extends UserViewEntry {

    public NextCourse(CustomButton[] buttons) {
        super("Next Course");
        this.addFirstRow(new String[]{"Fachhochschule Hagenberg", "07.05.2024"});
        this.addSecondRow(new String[]{"IT-Strategie","08:30"});
        this.addThirdRow(new String[]{"FHHA | Raum 202"});
        this.addButtonLayout(buttons);
    }
}

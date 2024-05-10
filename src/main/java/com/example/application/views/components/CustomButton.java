package com.example.application.views.components;

import com.vaadin.flow.component.button.Button;

public class CustomButton extends Button {
    public CustomButton(String text) {
        super(text);
        addClassName("app-button");
    }
}

package com.example.application.views.components;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;

public class CustomButton extends Button {
    public CustomButton(String text) {
        super(text);
        addClassName("app-button");
    }

    public CustomButton(String text, ComponentEventListener<ClickEvent<Button>> clickListener) {
        super(text, clickListener);
        super.addClassName("app-button");
    }
}

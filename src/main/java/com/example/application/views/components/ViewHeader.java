package com.example.application.views.components;

import com.vaadin.flow.component.html.H1;

public class ViewHeader extends H1 {
    public ViewHeader(String text) {
        super(text);
        this.addClassName("view-header");
    }
}

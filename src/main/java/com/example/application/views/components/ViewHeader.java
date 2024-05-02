package com.example.application.views.components;

import com.vaadin.flow.component.html.H3;

public class ViewHeader extends H3 {
    public ViewHeader(String text) {
        super(text);
        this.addClassName("view-header");
    }
}

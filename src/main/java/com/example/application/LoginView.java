package com.example.application;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Attend Login")
@CssImport("./styles/login-styles.css")
@Route("login")
public class LoginView extends HorizontalLayout {

    public LoginView() {
        addClassName("login-view-background");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.END);

        // Linker
        VerticalLayout textLayout = new VerticalLayout();
        textLayout.addClassName("text-layout");
        H1 mainTitle = new H1("attend.");
        mainTitle.addClassName("main-title");
        Span subTitle = new Span("Anwesenheit erfassen, Effizienz steigern");
        subTitle.addClassName("sub-title");
        textLayout.add(mainTitle, subTitle);

        // LoginForm
        LoginForm loginForm = createLoginForm();
        VerticalLayout formLayout = new VerticalLayout(loginForm);
        formLayout.addClassName("form-layout");


        // Footer
        Div footerText = new Div();
        footerText.setText("© 2024 De Java Dev´s");
        footerText.addClassName("footer-text");

        // Layout
        add(textLayout, formLayout);
        expand(textLayout);
        setFlexGrow(1, formLayout);
        add(footerText);
    }

    private LoginForm createLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.setId("login-form");
        loginForm.setAction("login");
        loginForm.setI18n(createLoginI18n());
        return loginForm;
    }

    private LoginI18n createLoginI18n() {
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.getForm().setTitle("");
        i18n.getForm().setUsername("e-mail");
        i18n.getForm().setPassword("password");
        i18n.getForm().setSubmit("login");
        i18n.getForm().setForgotPassword("forgot password?");
        return i18n;
    }
}
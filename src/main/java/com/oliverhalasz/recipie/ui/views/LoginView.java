package com.oliverhalasz.recipie.ui.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("login")
@PageTitle("Login | ReciPie")
public class LoginView extends Composite<LoginOverlay> {

    public LoginView() {
        getContent().setOpened(true);
        getContent().setAction("login");
        getContent().setTitle("ReciPie");
        getContent().setDescription("User 1: john + password USER 2: jane + password");
        getContent().setForgotPasswordButtonVisible(false);


    }
}

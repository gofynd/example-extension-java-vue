package com.fynd.example.java.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteControllerTest {

    private RouteController routeController;

    @BeforeEach
    public void setUp() {
        routeController = new RouteController();
    }

    @Test
    public void testRedirect() {
        String result = routeController.redirect();

        assertThat(result).isEqualTo("forward:/");
    }

    @Test
    public void testRedirectToAppHome() {
        String result = routeController.redirectToAppHome();

        assertThat(result).isEqualTo("forward:/");
    }
}
package com.fynd.example.java.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthControllerTest {

    private HealthController healthController;

    @BeforeEach
    public void setUp() {
        healthController = new HealthController();
    }

    @Test
    public void testHealth() {
        ResponseEntity<Map<String, String>> response = healthController.health();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).containsEntry("status", "up");
    }

    @Test
    public void testReady() {
        ResponseEntity<Map<String, String>> response = healthController.ready();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody()).containsEntry("status", "ready");
    }
}
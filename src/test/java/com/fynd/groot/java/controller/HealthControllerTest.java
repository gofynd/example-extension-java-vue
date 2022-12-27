package com.fynd.groot.java.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void health() {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/_healthz", String.class)).isEqualTo("{\"status\":\"up\"}");
    }

    @Test
    void ready() {
        assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/_readyz", String.class)).isEqualTo("{\"status\":\"ready\"}");
    }
}
package com.fynd.example.java.controller;

import com.fynd.extension.service.WebhookService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WebhookControllerTest {

    @Mock
    private WebhookService webhookService;

    @InjectMocks
    private WebhookController webhookController;

    @Mock
    private HttpServletRequest request;

    @Test
    public void testReceiveWebhookEvents_Success() {
        doNothing().when(webhookService).processWebhook(request);

        ResponseEntity<Object> response = webhookController.receiveWebhookEvents(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("success", true), response.getBody());

        verify(webhookService, times(1)).processWebhook(request);
    }

    @Test
    public void testReceiveWebhookEvents_Exception() {
        doThrow(new RuntimeException("Test Exception")).when(webhookService).processWebhook(request);

        ResponseEntity<Object> response = webhookController.receiveWebhookEvents(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonMap("success", false), response.getBody());

        verify(webhookService, times(1)).processWebhook(request);
    }
}




package com.fynd.example.java.webhook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@ExtendWith(OutputCaptureExtension.class)
public class SampleEventHandlerTest {

    private SampleEventHandler sampleEventHandler;

    private static final Logger log = LoggerFactory.getLogger(SampleEventHandler.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleEventHandler = new SampleEventHandler();
    }

    @Test
    void testHandle_shouldLogEvent(CapturedOutput output) {
        String eventName = "SampleEvent";
        String companyId = "123";
        String applicationId = "456";
        Object body = new Object();
        sampleEventHandler.handle(eventName, body, companyId, applicationId);
        assertTrue(output.getOut().contains("Event  Received : SampleEvent for companyId : 123"));
    }

    @Test
    void testHandle_shouldNotThrowException() {
        String eventName = "SampleEvent";
        String companyId = "123";
        String applicationId = "456";
        Object body = new Object();
        try {
            sampleEventHandler.handle(eventName, body, companyId, applicationId);
        } catch (Exception e) {
            fail("Method should not throw exception");
        }
    }
}
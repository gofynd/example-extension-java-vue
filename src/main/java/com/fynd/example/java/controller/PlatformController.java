package com.fynd.example.java.controller;

import com.fynd.extension.controllers.BasePlatformController;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.configuration.ConfigurationPlatformModels;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class PlatformController extends BasePlatformController {


    @GetMapping(value = "/applications", produces = "application/json")
    public ConfigurationPlatformModels.ApplicationsResponse getApplications(HttpServletRequest request) {
        try {
            System.out.println("What? I'm here!!!");
            PlatformClient platformClient = (PlatformClient) request.getAttribute("platformClient");
            System.out.println("I'm here!!!");
            ConfigurationPlatformModels.ApplicationsResponse applications
                    = platformClient.configuration.getApplications(1, 100, "");

            return applications;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}



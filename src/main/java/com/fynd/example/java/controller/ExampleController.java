package com.fynd.example.java.controller;


import com.fynd.example.java.service.ExampleService;
import com.sdk.platform.PlatformModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {

    @Autowired
    ExampleService exampleService;

    @GetMapping(value = "/applications", produces = "application/json")
    public PlatformModels.ApplicationsResponse getApplications() {
        return exampleService.getApplications();
    }

}

package com.fynd.groot.java.controller;



import com.fynd.groot.java.service.ExampleService;
import com.fynd.groot.java.service.impl.ExampleServiceImpl;
import com.sdk.platform.PlatformModels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ExampleController {

    @Autowired
    ExampleServiceImpl exampleService;

    @GetMapping(value = "/applications", produces = "application/json")
    public PlatformModels.ApplicationsResponse getApplications() {
        return exampleService.getApplications();
    }

}

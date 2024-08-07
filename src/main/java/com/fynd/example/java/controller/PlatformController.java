package com.fynd.example.java.controller;

import com.fynd.extension.controllers.BasePlatformController;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.catalog.CatalogPlatformModels;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@Slf4j
public class PlatformController extends BasePlatformController {


    @GetMapping(value = "/products", produces = "application/json")
    public CatalogPlatformModels.ProductListingResponseV2 getProducts(HttpServletRequest request) {
        try {
            PlatformClient platformClient = (PlatformClient) request.getAttribute("platformClient");
            return platformClient.catalog.getProducts(Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), "", Collections.emptyList(), 1, 10);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @GetMapping(value = "/{application_id}/products", produces = "application/json")
    public CatalogPlatformModels.RawProductListingResponse getAppProducts(@PathVariable("application_id") String applicationId, HttpServletRequest request) {
        try {
            PlatformClient platformClient = (PlatformClient) request.getAttribute("platformClient");
            return platformClient.application(applicationId).catalog.getAppProducts(Collections.emptyList(),Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), 1, 10, "");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}



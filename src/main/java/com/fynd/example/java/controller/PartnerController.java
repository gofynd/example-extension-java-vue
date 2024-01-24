package com.fynd.example.java.controller;

import com.fynd.extension.controllers.BasePartnerController;
import com.sdk.partner.PartnerClient;
import com.sdk.partner.theme.ThemePartnerModels;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.configuration.ConfigurationPlatformModels;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class PartnerController extends BasePartnerController {
    @GetMapping(value = "/partner/route", produces = "application/json")
    public ThemePartnerModels.MarketplaceThemeSchema getApplications(HttpServletRequest request) {
        try {
            PartnerClient partnerClient = (PartnerClient) request.getAttribute("partnerClient");
            ThemePartnerModels.MarketplaceThemeSchema response = partnerClient.theme.getOrganizationThemes("published", null, null);


            return response;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}

package com.fynd.example.java.controller;

import com.sdk.common.model.FDKException;
import com.sdk.common.model.FDKServerResponseError;
import com.sdk.platform.catalog.CatalogPlatformModels;
import jakarta.servlet.http.HttpServletRequest;

import com.sdk.platform.PlatformClient;
import com.sdk.platform.catalog.CatalogPlatformService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class PlatformControllerTest {

    @Mock
    private PlatformClient platformClient;

    @Mock
    private PlatformClient.ApplicationClient applicationClient;

    @Mock
    private CatalogPlatformService.ApplicationClient catalogApplicationClient;

    @Mock
    private CatalogPlatformService catalogPlatformService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private PlatformController platformController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        platformClient.catalog = catalogPlatformService;
        when(platformClient.application(anyString())).thenReturn(applicationClient);
        applicationClient.catalog = catalogApplicationClient;
        when(request.getAttribute("platformClient")).thenReturn(platformClient);
    }

    @Test
    void testGetAppProducts_Success() throws Exception {
        CatalogPlatformModels.RawProductListingResponse mockResponse = new CatalogPlatformModels.RawProductListingResponse();
        when(catalogApplicationClient.getAppProducts(anyList(), anyList(), anyList(), anyList(), anyList(), anyInt(), anyInt(), anyString()))
                .thenReturn(mockResponse);
        CatalogPlatformModels.RawProductListingResponse response = platformController.getAppProducts("application_id", request);
        assertEquals(mockResponse, response);
    }

    @Test
    void testGetAppProducts_Exception() throws FDKServerResponseError, FDKException {
        when(catalogApplicationClient.getAppProducts(anyList(), anyList(), anyList(), anyList(), anyList(), anyInt(), anyInt(), anyString()))
                .thenThrow(new RuntimeException("Error"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            platformController.getAppProducts("application_id", request);
        });
        assertEquals("java.lang.RuntimeException: Error", exception.getMessage());
    }

    @Test
    void testGetProducts_Success() throws Exception {
        CatalogPlatformModels.ProductListingResponseV2 mockResponse = new CatalogPlatformModels.ProductListingResponseV2();
        when(catalogPlatformService.getProducts(anyList(), anyList(), anyList(), anyList(), anyList(), anyString(), anyList(), anyInt(), anyInt()))
                .thenReturn(mockResponse);
        CatalogPlatformModels.ProductListingResponseV2 response = platformController.getProducts(request);
        assertEquals(mockResponse, response);
    }

    @Test
    void testGetProducts_Exception() throws FDKServerResponseError, FDKException {
        when(catalogPlatformService.getProducts(anyList(), anyList(), anyList(), anyList(), anyList(), anyString(), anyList(), anyInt(), anyInt()))
                .thenThrow(new RuntimeException("Error"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            platformController.getProducts(request);
        });
        assertEquals("java.lang.RuntimeException: Error", exception.getMessage());
    }

}

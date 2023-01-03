package com.fynd.groot.java.service;

import com.fynd.extension.utils.ExtensionContext;
import com.fynd.groot.java.controller.ExampleController;
import com.fynd.groot.java.service.impl.ExampleServiceImpl;
import com.sdk.platform.PlatformClient;
import com.sdk.platform.PlatformService;
import com.sdk.platform.PlatformModels;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class ExampleServiceTest {
    @InjectMocks
    ExampleServiceImpl exampleService;
    @Mock
    PlatformClient platformClient;

    @Mock
    PlatformService.ConfigurationService configurationService;

    @Test
    void getApplications() throws IOException {
        try(MockedStatic<ExtensionContext> context = Mockito.mockStatic(ExtensionContext.class)){

            context.when(()->ExtensionContext.get("platform-client", PlatformClient.class)).thenReturn(platformClient);

            PlatformModels.ApplicationsResponse res = new PlatformModels.ApplicationsResponse();

            ReflectionTestUtils.setField(platformClient, "configuration", configurationService);

            Mockito.when(configurationService.getApplications(anyInt(), anyInt(), anyString())).thenReturn(res);


            assertThat(exampleService.getApplications()).isEqualTo(res);
        }
    }
}

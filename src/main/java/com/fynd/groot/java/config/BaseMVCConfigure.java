package com.fynd.groot.java.config;


import com.fynd.extension.middleware.ApplicationInterceptor;
import com.fynd.extension.middleware.PlatformInterceptor;
import com.fynd.extension.middleware.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseMVCConfigure implements WebMvcConfigurer {
    @Autowired
    ApplicationInterceptor applicationInterceptor;

    @Autowired
    PlatformInterceptor platformInterceptor;

    @Autowired
    SessionInterceptor sessionInterceptor;

    public BaseMVCConfigure() {
    }

    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.platformInterceptor).addPathPatterns(new String[]{"/api/v1/**"}).order(Integer.MAX_VALUE);
    }
}

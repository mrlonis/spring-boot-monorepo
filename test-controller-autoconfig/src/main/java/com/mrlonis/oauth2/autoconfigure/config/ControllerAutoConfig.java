package com.mrlonis.oauth2.autoconfigure.config;

import com.mrlonis.oauth2.autoconfigure.controller.TestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerAutoConfig {
    @Bean
    public TestController testController() {
        return new TestController();
    }
}

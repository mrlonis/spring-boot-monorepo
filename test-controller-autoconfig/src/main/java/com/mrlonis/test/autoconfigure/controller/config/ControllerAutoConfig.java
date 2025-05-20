package com.mrlonis.test.autoconfigure.controller.config;

import com.mrlonis.test.autoconfigure.controller.controller.TestController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ControllerAutoConfig {
    @Bean
    public TestController testController() {
        return new TestController();
    }
}

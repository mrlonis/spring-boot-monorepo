package com.mrlonis.test.controller.autoconfigure.config;

import com.mrlonis.test.controller.autoconfigure.controller.TestController;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ControllerAutoConfig {
    @Bean
    public TestController testController() {
        return new TestController();
    }
}

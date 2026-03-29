package com.mrlonis.example.gateway.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {
    @NestedConfigurationProperty
    private RouteProperties routes;

    @Data
    public static class RouteProperties {
        private String googleUri;
    }
}

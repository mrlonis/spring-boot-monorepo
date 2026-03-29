package com.mrlonis.example.oauth2.opaque.config.properties;

import com.mrlonis.example.oauth2.opaque.model.OAuthRegistration;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    @NestedConfigurationProperty
    private Map<String, OAuthRegistration> clients;
}

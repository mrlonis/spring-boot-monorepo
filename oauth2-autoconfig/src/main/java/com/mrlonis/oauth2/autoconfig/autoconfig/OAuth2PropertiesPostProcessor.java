package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import com.mrlonis.oauth2.autoconfig.security.RequestAccess;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

@EnableConfigurationProperties(OAuth2AutoConfigurationProperties.class)
@AllArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true")
public class OAuth2PropertiesPostProcessor {
    private final OAuth2AutoConfigurationProperties properties;
    private final ConfigurableEnvironment environment;

    @PostConstruct
    public void applyDefaults() {
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("oauth2.security.default-any-request-access", RequestAccess.AUTHENTICATED);

        if (Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> StringUtils.equals("local", profile))
                && properties.getFederate().isEnabled()) {
            defaults.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", "http://localhost:9562");
            if (!properties.getFederate().isOpaque()) {
                defaults.put("oauth2.security.federate.issuer-uri", "http://localhost:9562");
                defaults.put("oauth2.security.federate.jwk-set-uri", "http://localhost:9562/oauth2/jwks");
            }
        }
        environment.getPropertySources().addFirst(new MapPropertySource("oauth2-autoconfig.defaults", defaults));
    }
}

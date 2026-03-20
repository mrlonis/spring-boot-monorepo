package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.security.RequestAccess;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.Strings;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

@AllArgsConstructor
public class OAuth2PropertiesEnvironmentPostProcessor
        implements EnvironmentPostProcessor, ApplicationListener<ApplicationContextInitializedEvent> {
    private static final DeferredLog log = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Setting default-any-request-access to AUTHENTICATED");
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("oauth2.security.default-any-request-access", RequestAccess.AUTHENTICATED.name());

        boolean isFederateEnabled = environment.getProperty("oauth2.federate.enabled", Boolean.class, false);
        if (Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> Strings.CS.equals("local", profile))
                && isFederateEnabled) {
            boolean isFederateOpaque = environment.getProperty("oauth2.federate.opaque", Boolean.class, false);
            if (!isFederateOpaque) {
                defaults.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", "http://localhost:9562");
                defaults.put("oauth2.federate.issuer-uri", "http://localhost:9562");
                defaults.put("oauth2.federate.jwk-set-uri", "http://localhost:9562/oauth2/jwks");
            } else {
                defaults.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", "http://localhost:9563");
                defaults.put(
                        "spring.security.oauth2.resourceserver.opaquetoken.introspection-uri",
                        "http://localhost:9563/oauth2/introspect");
            }
        }
        environment.getPropertySources().addFirst(new MapPropertySource("oauth2-autoconfig.defaults", defaults));
    }

    @Override
    public void onApplicationEvent(final @NonNull ApplicationContextInitializedEvent event) {
        log.replayTo(OAuth2PropertiesEnvironmentPostProcessor.class);
    }
}

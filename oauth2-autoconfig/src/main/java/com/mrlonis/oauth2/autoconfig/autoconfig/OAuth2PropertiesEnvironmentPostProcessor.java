package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.security.RequestAccess;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OAuth2PropertiesEnvironmentPostProcessor
        implements EnvironmentPostProcessor, ApplicationListener<ApplicationContextInitializedEvent> {
    private static final DeferredLog DEFERRED_LOG = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        DEFERRED_LOG.info("Setting oauth2.security.default-any-request-access to AUTHENTICATED");
        Map<String, Object> defaults = new HashMap<>();
        defaults.put("oauth2.security.default-any-request-access", RequestAccess.AUTHENTICATED.name());

        boolean isFederateEnabled = environment.getProperty("oauth2.federate.enabled", Boolean.class, false);
        DEFERRED_LOG.debug(String.format("oauth2.federate.enabled: %s", isFederateEnabled));
        if (Arrays.stream(environment.getActiveProfiles()).anyMatch(profile -> Strings.CS.equals("local", profile))
                && isFederateEnabled) {
            DEFERRED_LOG.debug("Federate is enabled and local profile is active");
            boolean isFederateOpaque = environment.getProperty("oauth2.federate.opaque", Boolean.class, false);
            DEFERRED_LOG.debug(String.format("oauth2.federate.opaque: %s", isFederateOpaque));
            if (!isFederateOpaque) {
                DEFERRED_LOG.debug("Federate is not opaque");
                DEFERRED_LOG.info(
                        "Setting spring.security.oauth2.resourceserver.jwt.issuer-uri to http://localhost:9562");
                defaults.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", "http://localhost:9562");
                DEFERRED_LOG.info("Setting oauth2.federate.issuer-uri to http://localhost:9562");
                defaults.put("oauth2.federate.issuer-uri", "http://localhost:9562");
                DEFERRED_LOG.info("Setting oauth2.federate.jwk-set-uri to http://localhost:9562/oauth2/jwks");
                defaults.put("oauth2.federate.jwk-set-uri", "http://localhost:9562/oauth2/jwks");
            } else {
                DEFERRED_LOG.debug("Federate is opaque");
                DEFERRED_LOG.info(
                        "Setting spring.security.oauth2.resourceserver.jwt.issuer-uri to http://localhost:9563");
                defaults.put("spring.security.oauth2.resourceserver.jwt.issuer-uri", "http://localhost:9563");
                DEFERRED_LOG.info(
                        "Setting spring.security.oauth2.resourceserver.opaquetoken.introspection-uri to http://localhost:9563/oauth2/introspect");
                defaults.put(
                        "spring.security.oauth2.resourceserver.opaquetoken.introspection-uri",
                        "http://localhost:9563/oauth2/introspect");
                DEFERRED_LOG.info(
                        "Setting oauth2.federate.introspection-uri to http://localhost:9563/oauth2/introspect");
                defaults.put("oauth2.federate.introspection-uri", "http://localhost:9563/oauth2/introspect");
            }
        }

        application.addListeners(this);

        environment.getPropertySources().addFirst(new MapPropertySource("oauth2-autoconfig.defaults", defaults));
    }

    @Override
    public void onApplicationEvent(final @NonNull ApplicationContextInitializedEvent event) {
        DEFERRED_LOG.replayTo(OAuth2PropertiesEnvironmentPostProcessor.class);
    }
}

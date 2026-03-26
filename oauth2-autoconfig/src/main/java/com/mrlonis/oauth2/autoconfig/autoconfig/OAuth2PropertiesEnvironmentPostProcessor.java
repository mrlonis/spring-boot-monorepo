package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.security.RequestAccess;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

/** This class is used to configure property defaults for applications that use this autoconfiguration jar. */
public class OAuth2PropertiesEnvironmentPostProcessor
        implements EnvironmentPostProcessor, ApplicationListener<ApplicationContextInitializedEvent> {
    private static final DeferredLog DEFERRED_LOG = new DeferredLog();

    /**
     * This method configures the Spring Boot environment with key properties needed for the remaining autoconfiguration
     * classes within this library to function properly. It sets default values for properties such as
     * `oauth2.security.default-any-request-access` and conditionally configures properties related to federated
     * authentication based on the active profiles and specific property settings. The method also registers this class
     * as an application listener to ensure that deferred logs are replayed once the application context is initialized.
     *
     * @param environment the environment to post-process
     * @param application the application to which the environment belongs
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Map<String, Object> defaults = new HashMap<>();

        putIfMissing(
                environment,
                defaults,
                "oauth2.security.default-any-request-access",
                RequestAccess.AUTHENTICATED.name());

        boolean isFederateEnabled = environment.getProperty("oauth2.federate.enabled", Boolean.class, false);
        DEFERRED_LOG.debug("oauth2.federate.enabled: " + isFederateEnabled);

        boolean isLocal = Arrays.asList(environment.getActiveProfiles()).contains("local");

        if (isLocal && isFederateEnabled) {
            DEFERRED_LOG.debug("Federate is enabled and local profile is active");

            boolean isFederateOpaque = environment.getProperty("oauth2.federate.opaque", Boolean.class, false);
            DEFERRED_LOG.debug("oauth2.federate.opaque: " + isFederateOpaque);

            if (!isFederateOpaque) {
                DEFERRED_LOG.debug("Federate is not opaque");
                putIfMissing(environment, defaults, "oauth2.federate.issuer-uri", "http://localhost:9562");
                putIfMissing(environment, defaults, "oauth2.federate.jwk-set-uri", "http://localhost:9562/oauth2/jwks");
            } else {
                DEFERRED_LOG.debug("Federate is opaque");
                putIfMissing(
                        environment,
                        defaults,
                        "oauth2.federate.introspection-uri",
                        "http://localhost:9563/oauth2/introspect");
            }
        }

        // Register this class as an application listener to ensure that deferred logs are replayed once the application
        // context is initialized and the logging system is fully set up. This allows us to see the debug logs that were
        // generated during the postProcessEnvironment method, which would otherwise not be visible due to the logging
        // system not being initialized at that point.
        application.addListeners(this);

        environment.getPropertySources().addLast(new MapPropertySource("oauth2-autoconfig.defaults", defaults));
    }

    /**
     * Puts the provided key/value pair into the provided map if the key does not exist in the provided environment.
     *
     * @param environment the environment to check for the presence of the property
     * @param defaults the map of default properties to which the key-value pair will be added if the property is not
     *     already set in the environment
     * @param key the property key to check in the environment and to add to the defaults if it is missing
     * @param value the property value to add to the defaults if the key is missing in the environment
     */
    private static void putIfMissing(
            ConfigurableEnvironment environment, Map<String, Object> defaults, String key, Object value) {
        if (!environment.containsProperty(key)) {
            DEFERRED_LOG.info("Setting " + key + " to " + value + " since it is not already set");
            defaults.put(key, value);
        }
    }

    /**
     * This method is used to replay the deferred logs once the application context is initialized.
     *
     * <p>This is necessary because {@link EnvironmentPostProcessor} run so early on in the process that the logging
     * systems are not initialized. Therefore, all the operations we perform inside postProcessEnvironment that involve
     * logging will not actually log anything if we were to use a normal logger. Hence, why we use a {@link DeferredLog}
     * instead
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(final @NonNull ApplicationContextInitializedEvent event) {
        DEFERRED_LOG.replayTo(OAuth2PropertiesEnvironmentPostProcessor.class);
    }
}

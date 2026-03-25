package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;

@EnableConfigurationProperties(OAuth2AutoConfigurationProperties.class)
@AllArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true")
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@Slf4j
public class ReactiveOpaqueTokenIntrospectorAutoConfiguration {
    private final OAuth2AutoConfigurationProperties properties;

    @Bean
    @ConditionalOnProperty(name = "oauth2.federate.enabled", havingValue = "true")
    @ConditionalOnProperty(name = "oauth2.federate.opaque", havingValue = "true")
    public ReactiveOpaqueTokenIntrospector reactiveOpaqueTokenIntrospector() {
        var delegate = new NimbusReactiveOpaqueTokenIntrospector(
                properties.getFederate().getIntrospectionUri(),
                properties.getFederate().getClientId(),
                properties.getFederate().getClientSecret());

        return token -> delegate.introspect(token).map(principal -> {
            Object audClaim = principal.getAttribute("aud");

            log.debug(
                    "Validating audience claim {} against allowed audiences {}",
                    audClaim,
                    properties.getFederate().getAudiences());

            boolean valid = false;

            if (audClaim instanceof String aud) {
                valid = properties.getFederate().getAudiences().contains(aud);
            } else if (audClaim instanceof Collection<?> auds) {
                valid = auds.stream()
                        .anyMatch(a -> properties.getFederate().getAudiences().contains(a));
            }

            if (!valid) {
                throw new OAuth2AuthenticationException("Invalid audience");
            }

            return principal;
        });
    }
}

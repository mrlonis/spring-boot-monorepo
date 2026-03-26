package com.mrlonis.oauth2.autoconfig.autoconfig;

import static com.mrlonis.oauth2.autoconfig.util.AudienceValidator.INVALID_TOKEN_ERROR;
import static com.mrlonis.oauth2.autoconfig.util.AudienceValidator.isValidAudience;

import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringReactiveOpaqueTokenIntrospector;

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
        log.debug(
                "Configuring ReactiveOpaqueTokenIntrospector with Introspection URI: {}",
                properties.getFederate().getIntrospectionUri());

        var delegate = SpringReactiveOpaqueTokenIntrospector.withIntrospectionUri(
                        properties.getFederate().getIntrospectionUri())
                .clientId(properties.getFederate().getClientId())
                .clientSecret(properties.getFederate().getClientSecret())
                .build();

        return token -> delegate.introspect(token).map(principal -> {
            Object audClaim = principal.getAttribute("aud");

            log.debug(
                    "Validating audience claim {} against allowed audiences {}",
                    audClaim,
                    properties.getFederate().getAudiences());

            if (!isValidAudience(audClaim, properties.getFederate().getAudiences())) {
                throw new OAuth2AuthenticationException(INVALID_TOKEN_ERROR);
            }

            return principal;
        });
    }
}

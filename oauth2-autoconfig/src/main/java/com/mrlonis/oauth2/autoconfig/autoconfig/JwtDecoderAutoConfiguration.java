package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@EnableConfigurationProperties(OAuth2AutoConfigurationProperties.class)
@AllArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true")
@Slf4j
public class JwtDecoderAutoConfiguration {
    private final OAuth2AutoConfigurationProperties properties;

    @Bean
    @ConditionalOnProperty(name = "oauth2.federate.enabled", havingValue = "true")
    @ConditionalOnProperty(name = "oauth2.federate.opaque", havingValue = "false", matchIfMissing = true)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public JwtDecoder jwtDecoder() {
        log.debug(
                "Configuring JwtDecoder with JWK Set URI: {}",
                properties.getFederate().getJwkSetUri());
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(
                        properties.getFederate().getJwkSetUri())
                .build();
        log.debug(
                "Setting up JWT validator with issuer: {} and audiences: {}",
                properties.getFederate().getIssuerUri(),
                properties.getFederate().getAudiences());
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(properties.getFederate().getIssuerUri()),
                buildAudienceValidator(properties));
        log.debug("JWT validator configured: {}", validator);
        decoder.setJwtValidator(validator);
        return decoder;
    }

    @Bean
    @ConditionalOnProperty(name = "oauth2.federate.enabled", havingValue = "true")
    @ConditionalOnProperty(name = "oauth2.federate.opaque", havingValue = "false", matchIfMissing = true)
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        log.debug(
                "Configuring ReactiveJwtDecoder with JWK Set URI: {}",
                properties.getFederate().getJwkSetUri());
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withJwkSetUri(
                        properties.getFederate().getJwkSetUri())
                .build();
        log.debug(
                "Setting up Reactive JWT validator with issuer: {} and audiences: {}",
                properties.getFederate().getIssuerUri(),
                properties.getFederate().getAudiences());
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(properties.getFederate().getIssuerUri()),
                buildAudienceValidator(properties));
        log.debug("Reactive JWT validator configured: {}", validator);
        decoder.setJwtValidator(validator);
        return decoder;
    }

    private static JwtClaimValidator<List<String>> buildAudienceValidator(
            OAuth2AutoConfigurationProperties properties) {
        log.debug(
                "Building audience validator with expected audiences: {}",
                properties.getFederate().getAudiences());
        return new JwtClaimValidator<>(JwtClaimNames.AUD, aud -> {
            log.debug(
                    "Validating audience claim {} against allowed audiences {}",
                    aud,
                    properties.getFederate().getAudiences());
            return aud != null
                    && CollectionUtils.containsAny(properties.getFederate().getAudiences(), aud);
        });
    }
}

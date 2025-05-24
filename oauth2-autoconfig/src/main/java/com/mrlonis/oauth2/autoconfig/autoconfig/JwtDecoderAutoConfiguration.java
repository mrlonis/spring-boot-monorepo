package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import java.util.List;
import lombok.AllArgsConstructor;
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
public class JwtDecoderAutoConfiguration {
    private final OAuth2AutoConfigurationProperties properties;

    @Bean
    @ConditionalOnProperty(name = "oauth2.federate.enabled", havingValue = "true")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder decoder = NimbusJwtDecoder.withJwkSetUri(
                        properties.getFederate().getJwkSetUri())
                .build();
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(properties.getFederate().getIssuerUri()),
                buildAudienceValidator(properties));
        decoder.setJwtValidator(validator);
        return decoder;
    }

    @Bean
    @ConditionalOnProperty(name = "oauth2.federate.enabled", havingValue = "true")
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    public ReactiveJwtDecoder reactiveJwtDecoder() {
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withJwkSetUri(
                        properties.getFederate().getJwkSetUri())
                .build();
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(properties.getFederate().getIssuerUri()),
                buildAudienceValidator(properties));
        decoder.setJwtValidator(validator);
        return decoder;
    }

    private static JwtClaimValidator<List<String>> buildAudienceValidator(
            OAuth2AutoConfigurationProperties properties) {
        return new JwtClaimValidator<>(
                JwtClaimNames.AUD,
                aud -> aud != null
                        && CollectionUtils.containsAny(properties.getFederate().getAudiences(), aud));
    }
}

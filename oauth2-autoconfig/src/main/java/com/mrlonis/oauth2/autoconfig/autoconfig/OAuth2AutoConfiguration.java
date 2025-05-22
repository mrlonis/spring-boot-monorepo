package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.properties.AutoConfigurationProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtClaimValidator;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@AllArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true")
public class OAuth2AutoConfiguration {
    private final AutoConfigurationProperties properties;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> authorize
                .pathMatchers("/actuator/**", "/google/**")
                .permitAll()
                .anyExchange()
                .authenticated());
        if (properties.getFederate().isEnabled()) {
            http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
        }
        if (properties.getOidc().isEnabled()) {
            http.oauth2Login(Customizer.withDefaults());
        }
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withJwkSetUri(
                        properties.getFederate().getJwkSetUri())
                .build();

        JwtClaimValidator<List<String>> audienceValidator = new JwtClaimValidator<>(
                JwtClaimNames.AUD,
                aud -> aud != null
                        && CollectionUtils.containsAny(properties.getFederate().getAudiences(), aud));

        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(properties.getFederate().getIssuerUri()), audienceValidator);

        decoder.setJwtValidator(validator);
        return decoder;
    }
}

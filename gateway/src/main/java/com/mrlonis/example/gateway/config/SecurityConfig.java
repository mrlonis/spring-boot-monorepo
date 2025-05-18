package com.mrlonis.example.gateway.config;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
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
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private final OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorize -> authorize
                        .pathMatchers("/actuator/**", "/google/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(jwtDecoder())));
        return http.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        NimbusReactiveJwtDecoder decoder = NimbusReactiveJwtDecoder.withJwkSetUri(
                        oAuth2ResourceServerProperties.getJwt().getIssuerUri() + "/oauth2/jwks")
                .build();

        JwtClaimValidator<List<String>> audienceValidator =
                new JwtClaimValidator<>(JwtClaimNames.AUD, aud -> aud != null && aud.contains("gateway-client"));

        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
                JwtValidators.createDefaultWithIssuer(
                        oAuth2ResourceServerProperties.getJwt().getIssuerUri()),
                audienceValidator);

        decoder.setJwtValidator(validator);
        return decoder;
    }
}

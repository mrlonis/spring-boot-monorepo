package com.mrlonis.oauth2.autoconfig.autoconfig;

import com.mrlonis.oauth2.autoconfig.exception.OAuth2AutoConfigException;
import com.mrlonis.oauth2.autoconfig.properties.OAuth2AutoConfigurationProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableConfigurationProperties(OAuth2AutoConfigurationProperties.class)
@AllArgsConstructor
@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true")
@Slf4j
public class OAuth2AutoConfiguration {
    private final OAuth2AutoConfigurationProperties properties;

    @AllArgsConstructor(onConstructor_ = {@Autowired(required = false)})
    @Configuration
    @EnableWebSecurity
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    class OAuth2ServletAutoConfiguration {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            if (properties.getSecurity().isEnabled()) {
                log.debug(
                        "Configuring security filter chain with matchers {} and default any request access: {}",
                        properties.getSecurity().getMatchers(),
                        properties.getSecurity().getDefaultAnyRequestAccess());
                if (properties.getSecurity().getDefaultAnyRequestAccess() != null
                        || CollectionUtils.isNotEmpty(properties.getSecurity().getMatchers())) {
                    http.authorizeHttpRequests(authorize -> {
                        for (var matcher : properties.getSecurity().getMatchers()) {
                            if (matcher.getAccess() != null) {
                                switch (matcher.getAccess()) {
                                    case PERMIT_ALL ->
                                        authorize
                                                .requestMatchers(
                                                        matcher.getPaths().toArray(new String[0]))
                                                .permitAll();
                                    case DENY_ALL ->
                                        authorize
                                                .requestMatchers(
                                                        matcher.getPaths().toArray(new String[0]))
                                                .denyAll();
                                    case AUTHENTICATED, SPECIFIC_AUTHORITIES ->
                                        authorize
                                                .requestMatchers(
                                                        matcher.getPaths().toArray(new String[0]))
                                                .hasAnyAuthority(
                                                        matcher.getAuthorities().toArray(new String[0]));
                                    default -> throw new OAuth2AutoConfigException();
                                }
                            }
                        }
                        if (properties.getSecurity().getDefaultAnyRequestAccess() != null) {
                            switch (properties.getSecurity().getDefaultAnyRequestAccess()) {
                                case PERMIT_ALL -> authorize.anyRequest().permitAll();
                                case DENY_ALL -> authorize.anyRequest().denyAll();
                                case AUTHENTICATED -> authorize.anyRequest().authenticated();
                            }
                        }
                    });
                }
                if (properties.getFederate().isEnabled()) {
                    if (properties.getFederate().isOpaque()) {
                        log.debug("Configuring opaque token resource server");
                        http.oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()));
                    } else {
                        log.debug("Configuring JWT resource server");
                        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
                    }
                }
                if (properties.getOidc().isEnabled()) {
                    log.debug("Configuring OIDC login for servlet");
                    http.oauth2Login(Customizer.withDefaults());
                }
            }
            return http.build();
        }
    }

    @AllArgsConstructor(onConstructor_ = {@Autowired(required = false)})
    @Configuration
    @EnableWebFluxSecurity
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
    class OAuth2ReactiveAutoConfiguration {
        @Bean
        public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
            if (properties.getSecurity().isEnabled()) {
                log.debug(
                        "Configuring reactive security filter chain with matchers {} and default any request access: {}",
                        properties.getSecurity().getMatchers(),
                        properties.getSecurity().getDefaultAnyRequestAccess());
                if (properties.getSecurity().getDefaultAnyRequestAccess() != null
                        || CollectionUtils.isNotEmpty(properties.getSecurity().getMatchers())) {
                    http.authorizeExchange(authorize -> {
                        for (var matcher : properties.getSecurity().getMatchers()) {
                            if (matcher.getAccess() != null) {
                                switch (matcher.getAccess()) {
                                    case PERMIT_ALL ->
                                        authorize
                                                .pathMatchers(matcher.getPaths().toArray(new String[0]))
                                                .permitAll();
                                    case DENY_ALL ->
                                        authorize
                                                .pathMatchers(matcher.getPaths().toArray(new String[0]))
                                                .denyAll();
                                    case AUTHENTICATED, SPECIFIC_AUTHORITIES ->
                                        authorize
                                                .pathMatchers(matcher.getPaths().toArray(new String[0]))
                                                .hasAnyAuthority(
                                                        matcher.getAuthorities().toArray(new String[0]));
                                    default -> throw new OAuth2AutoConfigException();
                                }
                            }
                        }
                        if (properties.getSecurity().getDefaultAnyRequestAccess() != null) {
                            switch (properties.getSecurity().getDefaultAnyRequestAccess()) {
                                case PERMIT_ALL -> authorize.anyExchange().permitAll();
                                case DENY_ALL -> authorize.anyExchange().denyAll();
                                case AUTHENTICATED -> authorize.anyExchange().authenticated();
                            }
                        }
                    });
                }
                if (properties.getFederate().isEnabled()) {
                    if (properties.getFederate().isOpaque()) {
                        log.debug("Configuring reactive opaque token resource server");
                        http.oauth2ResourceServer(oauth2 -> oauth2.opaqueToken(Customizer.withDefaults()));
                    } else {
                        log.debug("Configuring reactive JWT resource server");
                        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
                    }
                }
                if (properties.getOidc().isEnabled()) {
                    log.debug("Configuring OIDC login for reactive");
                    http.oauth2Login(Customizer.withDefaults());
                }
            }
            return http.build();
        }
    }
}

package com.mrlonis.example.security.opaque.config;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.SpringOpaqueTokenIntrospector;

@Configuration
public class OpaqueTokenValidationConfig {
    @Bean
    OpaqueTokenIntrospector introspector(OAuth2ResourceServerProperties props) {
        var opaque = props.getOpaquetoken();
        var delegate = new SpringOpaqueTokenIntrospector(
                opaque.getIntrospectionUri(), opaque.getClientId(), opaque.getClientSecret());
        return token -> {
            OAuth2AuthenticatedPrincipal principal = delegate.introspect(token);
            List<String> audiences = principal.getAttribute("aud");
            if (audiences == null) {
                audiences = new ArrayList<>();
            }
            if (!CollectionUtils.containsAny(List.of("spring-security-opaque-client"), audiences)) {
                throw new OAuth2IntrospectionException("Token not issued for expected client");
            }
            return principal;
        };
    }
}

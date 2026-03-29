package com.mrlonis.example.oauth2.opaque.security;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocalOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private static final OAuth2Error INVALID_TOKEN = new OAuth2Error("invalid_token", "Token is invalid", null);

    private final OAuth2AuthorizationService authorizationService;

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        OAuth2Authorization authorization = this.authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);

        if (authorization == null) {
            throw new OAuth2AuthenticationException(INVALID_TOKEN);
        }

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();

        if (accessToken == null || accessToken.getToken() == null) {
            throw new OAuth2AuthenticationException(INVALID_TOKEN);
        }

        OAuth2AccessToken tokenValue = accessToken.getToken();

        Instant expiresAt = tokenValue.getExpiresAt();
        if (expiresAt != null && expiresAt.isBefore(Instant.now())) {
            throw new OAuth2AuthenticationException(INVALID_TOKEN);
        }

        Instant notBefore = tokenValue.getIssuedAt();
        if (notBefore != null && notBefore.isAfter(Instant.now())) {
            throw new OAuth2AuthenticationException(INVALID_TOKEN);
        }

        Map<String, Object> attributes = new HashMap<>();

        attributes.put("active", true);
        attributes.put("client_id", authorization.getRegisteredClientId());
        attributes.put("scope", tokenValue.getScopes());

        if (authorization.getPrincipalName() != null) {
            attributes.put("sub", authorization.getPrincipalName());
            attributes.put("username", authorization.getPrincipalName());
        }

        if (tokenValue.getIssuedAt() != null) {
            attributes.put("iat", tokenValue.getIssuedAt().getEpochSecond());
        }

        if (tokenValue.getExpiresAt() != null) {
            attributes.put("exp", tokenValue.getExpiresAt().getEpochSecond());
        }

        if (accessToken.getClaims() != null) {
            attributes.putAll(accessToken.getClaims());
        }

        Collection<GrantedAuthority> authorities = tokenValue.getScopes().stream()
                .filter(Objects::nonNull)
                .map(scope -> new SimpleGrantedAuthority("SCOPE_" + scope))
                .collect(Collectors.toSet());
        String name;

        if (authorization.getPrincipalName() != null) {
            name = authorization.getPrincipalName();
        } else {
            name = authorization.getRegisteredClientId();
        }

        return new DefaultOAuth2AuthenticatedPrincipal(name, attributes, authorities);
    }
}

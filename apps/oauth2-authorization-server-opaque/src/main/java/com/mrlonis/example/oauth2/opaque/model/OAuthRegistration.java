package com.mrlonis.example.oauth2.opaque.model;

import com.mrlonis.example.oauth2.opaque.model.enums.AuthorizationGrantTypes;
import com.mrlonis.example.oauth2.opaque.model.enums.ClientAuthenticationMethods;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
public class OAuthRegistration {
    private String clientId;

    private String clientSecret;
    private String clientSecrets;

    private ClientAuthenticationMethods clientAuthenticationMethod;

    private AuthorizationGrantTypes authorizationGrantType;

    private Set<String> redirectUris;

    private String postLogoutRedirectUri;

    private Set<String> scopes;
    private Set<String> scopes2;

    @NestedConfigurationProperty
    private ClientSettings clientSettings;
}

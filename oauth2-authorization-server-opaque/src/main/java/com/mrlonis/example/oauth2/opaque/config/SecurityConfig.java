package com.mrlonis.example.oauth2.opaque.config;

import static com.mrlonis.example.oauth2.opaque.util.PemUtils.generateRsaKey;

import com.mrlonis.example.oauth2.opaque.config.properties.ApplicationProperties;
import com.mrlonis.example.oauth2.opaque.model.OAuthRegistration;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.InMemoryOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    private final ApplicationProperties applicationProperties;

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                OAuth2AuthorizationServerConfigurer.authorizationServer();

        RequestMatcher endpoints = authorizationServerConfigurer.getEndpointsMatcher();

        http.securityMatcher(endpoints)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**", "/error", "/logout", "/oauth2/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(res -> res.opaqueToken(Customizer.withDefaults()))
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), request -> true))
                .with(authorizationServerConfigurer, Customizer.withDefaults());

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**", "/error", "/favicon.ico", "/oauth2/**", "/logout")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(res -> res.opaqueToken(Customizer.withDefaults()))
                .exceptionHandling(exceptions -> exceptions.defaultAuthenticationEntryPointFor(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED), request -> true));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository() {
        List<RegisteredClient> registeredClients = new ArrayList<>();
        log.debug(applicationProperties.getClients().toString());
        for (OAuthRegistration client : applicationProperties.getClients().values()) {
            log.debug("Registering client: {}", client);
            var registeredClientBuilder = RegisteredClient.withId(
                            UUID.randomUUID().toString())
                    .clientId(client.getClientId())
                    .clientAuthenticationMethod(new ClientAuthenticationMethod(
                            client.getClientAuthenticationMethod().name().toLowerCase()))
                    .authorizationGrantType(new AuthorizationGrantType(
                            client.getAuthorizationGrantType().name().toLowerCase()));
            if (client.getClientSecret() != null) {
                registeredClientBuilder.clientSecret(client.getClientSecret());
            }
            for (String redirectUri : client.getRedirectUris()) {
                registeredClientBuilder.redirectUri(redirectUri);
            }
            if (client.getPostLogoutRedirectUri() != null) {
                registeredClientBuilder.postLogoutRedirectUri(client.getPostLogoutRedirectUri());
            }
            for (String scope : client.getScopes()) {
                registeredClientBuilder.scope(scope);
            }
            var clientSettingsBuilder = ClientSettings.builder()
                    .requireAuthorizationConsent(client.getClientSettings().isRequireAuthorizationConsent())
                    .requireProofKey(client.getClientSettings().isRequireProofKey());
            registeredClientBuilder.clientSettings(clientSettingsBuilder.build());
            registeredClientBuilder.tokenSettings(TokenSettings.builder()
                    .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                    .build());
            registeredClients.add(registeredClientBuilder.build());
        }

        return new InMemoryRegisteredClientRepository(registeredClients);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource()
            throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        RSAKey rsaKey = generateRsaKey("id_rsa", "id_rsa.pub");
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public OAuth2AuthorizationService authorizationService() {
        return new InMemoryOAuth2AuthorizationService();
    }
}

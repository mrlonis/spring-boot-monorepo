package com.mrlonis.oauth2.autoconfig.properties;

import com.mrlonis.oauth2.autoconfig.security.MatcherRequestAccess;
import com.mrlonis.oauth2.autoconfig.security.RequestAccess;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Data
@ConfigurationProperties(prefix = "oauth2")
public class OAuth2AutoConfigurationProperties {
    private boolean enabled = false;

    @NestedConfigurationProperty
    private SecurityConfiguration security = new SecurityConfiguration();

    @NestedConfigurationProperty
    private FederateConfiguration federate = new FederateConfiguration();

    @NestedConfigurationProperty
    private OidcConfiguration oidc = new OidcConfiguration();

    @Data
    public static class SecurityConfiguration {
        private boolean enabled = true;
        private RequestAccess defaultAnyRequestAccess = RequestAccess.DENY_ALL;

        @NestedConfigurationProperty
        private List<Matcher> matchers = new ArrayList<>();

        @Data
        public static class Matcher {
            private MatcherRequestAccess access;
            private Set<String> authorities = new HashSet<>();
            private Set<String> paths = new HashSet<>();
        }
    }

    @Data
    public static class FederateConfiguration {
        private boolean enabled = false;
        private boolean opaque = false;
        private String issuerUri;
        private String jwkSetUri;
        private String introspectionUri;
        private Set<String> audiences = new HashSet<>();
        private String clientId;
        private String clientSecret;
    }

    @Data
    public static class OidcConfiguration {
        private boolean enabled = false;
    }
}

package com.mrlonis.oauth2.autoconfig.util;

import java.util.Collection;
import lombok.experimental.UtilityClass;
import org.springframework.security.oauth2.core.OAuth2Error;

@UtilityClass
public final class AudienceValidator {
    public static final OAuth2Error INVALID_TOKEN_ERROR = new OAuth2Error("invalid_token", "Invalid audience", null);

    public static boolean isValidAudience(Object audClaim, Collection<String> allowedAudiences) {
        if (audClaim instanceof String aud) {
            return allowedAudiences.contains(aud);
        }

        if (audClaim instanceof Collection<?> auds) {
            return auds.stream().allMatch(String.class::isInstance)
                    && auds.stream().map(String.class::cast).anyMatch(allowedAudiences::contains);
        }

        return false;
    }
}

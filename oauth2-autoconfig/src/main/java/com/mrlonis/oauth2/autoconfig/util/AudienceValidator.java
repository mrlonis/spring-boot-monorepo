package com.mrlonis.oauth2.autoconfig.util;

import java.util.Collection;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class AudienceValidator {
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

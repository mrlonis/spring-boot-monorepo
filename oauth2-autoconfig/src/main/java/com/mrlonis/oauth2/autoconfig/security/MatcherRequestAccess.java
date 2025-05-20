package com.mrlonis.oauth2.autoconfig.security;

public enum MatcherRequestAccess {
    DENY_ALL,
    AUTHENTICATED,
    PERMIT_ALL,
    SPECIFIC_AUTHORITIES
}

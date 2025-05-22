package com.mrlonis.oauth2.autoconfig.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@AutoConfiguration
@ConditionalOnProperty(name = "oauth2.enabled", havingValue = "true", matchIfMissing = true)
public class OAuth2AutoConfiguration {}

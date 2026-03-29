# oauth2-autoconfig

This module is a reusable Spring Boot auto-configuration library for the security samples in this repo.

## What It Provides

- Servlet and reactive security auto-configuration
- Matcher-based authorization rules driven by `oauth2.*` properties
- Resource server setup for JWT or opaque token federation
- Optional OIDC login wiring

The library activates only when `oauth2.enabled=true`.

## Typical Configuration

```yaml
oauth2:
  enabled: true
  security:
    enabled: true
    matchers:
      - paths:
          - /actuator/**
        access: permit_all
  federate:
    enabled: true
    audiences: spring-security-client
  oidc:
    enabled: false
```

Set `oauth2.federate.opaque=true` when the downstream resource server should use introspection instead of JWT validation.

## Related Modules

- [spring-security](../../apps/spring-security), [spring-security-reactive](../../apps/spring-security-reactive), [spring-security-opaque](../../apps/spring-security-opaque), and [spring-security-reactive-opaque](../../apps/spring-security-reactive-opaque) all consume this module.
- [gateway](../../apps/gateway) also uses it for OIDC and resource-server configuration.

# oauth2-authorization-server-opaque

This module is a Spring Authorization Server sample configured for opaque access tokens and token introspection.

## What It Demonstrates

- Authorization Server endpoints backed by Spring Security
- Opaque token issuance and introspection
- Local clients for the opaque resource server samples in this repo
- A focused place to compare opaque tokens with the JWT variant in [oauth2-authorization-server](../oauth2-authorization-server)

The configured local clients include `spring-security-opaque-client`, `spring-security-reactive-opaque-client`, and a small `test-client` flow for test coverage.

## Run Locally

```bash
./mvnw -pl oauth2-authorization-server-opaque spring-boot:run -Dspring-boot.run.profiles=local
```

The current local port defaults for this module are tracked in the [root port catalog](../README.md#local-profiles-and-ports).

## Related Modules

- [spring-security-opaque](../spring-security-opaque) and [spring-security-reactive-opaque](../spring-security-reactive-opaque) use this module in their local profile.
- [newman](../newman) includes a Postman collection for this app.

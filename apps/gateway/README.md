# gateway

This module is a reactive Spring Cloud Gateway sample that combines routing, Redis-backed sessions, OIDC login, and custom gateway filters.

## What It Demonstrates

- Spring Cloud Gateway Server WebFlux routing
- Session persistence with Spring Session and Redis
- OIDC login against [oauth2-authorization-server](../oauth2-authorization-server)
- Shared security wiring from [oauth2-autoconfig](../../starters/oauth2-autoconfig)
- A simple session endpoint at `GET /v1/session/id`

The current local route example forwards `/google/**` to the configured external URI and strips the leading path segment.

## Run Locally

```bash
./mvnw -pl gateway spring-boot:run -Dspring-boot.run.profiles=local
```

The current local port defaults for this module and its supporting services are tracked in the [root port catalog](../../README.md#local-profiles-and-ports).

## Related Modules

- [oauth2-authorization-server](../oauth2-authorization-server) provides the OIDC issuer used by the gateway.
- [oauth2-autoconfig](../../starters/oauth2-autoconfig) supplies the reusable security auto-configuration.
- [newman](../../newman) includes a Postman collection for this app.

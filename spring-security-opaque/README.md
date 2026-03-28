# spring-security-opaque

This module is a servlet-based Spring Security resource server sample configured for opaque token federation.

## What It Demonstrates

- Servlet security with shared config from [oauth2-autoconfig](../oauth2-autoconfig)
- Opaque token resource server support
- Protection of the auto-configured `GET /v1/test` endpoint

## Run Locally

```bash
./mvnw -pl spring-security-opaque spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile runs on `http://localhost:9055` and supplies the client credentials needed to introspect tokens through [oauth2-authorization-server-opaque](../oauth2-authorization-server-opaque).

## Related Modules

- [oauth2-authorization-server-opaque](../oauth2-authorization-server-opaque) is the local opaque token issuer and introspection endpoint.
- [test-controller-autoconfig](../test-controller-autoconfig) provides the `/v1/test` endpoint used in integration tests.

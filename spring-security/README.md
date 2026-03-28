# spring-security

This module is a servlet-based Spring Security resource server sample configured for JWT-based federation.

## What It Demonstrates

- Servlet security with shared config from [oauth2-autoconfig](../oauth2-autoconfig)
- JWT resource server support
- Protection of the auto-configured `GET /v1/test` endpoint

## Run Locally

```bash
./mvnw -pl spring-security spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile runs on `http://localhost:9054` and expects the JWT issuer from [oauth2-authorization-server](../oauth2-authorization-server) to be available on `http://localhost:9562`.

## Related Modules

- [oauth2-authorization-server](../oauth2-authorization-server) is the local JWT issuer.
- [test-controller-autoconfig](../test-controller-autoconfig) provides the `/v1/test` endpoint used in integration tests.

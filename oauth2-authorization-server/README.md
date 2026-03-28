# oauth2-authorization-server

This module is a Spring Authorization Server sample configured for JWT access tokens and OIDC login flows.

## What It Demonstrates

- Authorization Server endpoints backed by Spring Security
- JWT issuance for the servlet and reactive resource server samples
- OIDC login for the [gateway](../gateway) module
- A small set of local clients for app-to-app and Postman testing

The configured local clients include `oidc-client`, `gateway-client`, `postman-public-client`, `spring-security-client`, and `spring-security-reactive-client`.

## Run Locally

```bash
./mvnw -pl oauth2-authorization-server spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile runs on `http://localhost:9562`.

## Related Modules

- [gateway](../gateway) uses this server for OIDC login.
- [spring-security](../spring-security) and [spring-security-reactive](../spring-security-reactive) use it as their JWT issuer.
- [newman](../newman) includes a Postman collection for this app.

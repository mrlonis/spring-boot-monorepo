# oauth2-gateway-mvc

This module is a minimal Spring Cloud Gateway Server MVC sample. It currently acts as a lightweight starting point for MVC gateway experiments and basic actuator coverage.

## What It Demonstrates

- Bootstrapping `spring-cloud-gateway-server-webmvc`
- Session infrastructure from `spring-session-core`
- A minimal runnable app that can be expanded with MVC gateway routes and filters

Unlike the reactive [gateway](../gateway) module, this sample does not ship custom routes by default.

## Run Locally

```bash
./mvnw -pl oauth2-gateway-mvc spring-boot:run -Dspring-boot.run.profiles=local
```

The current local port defaults for this module are tracked in the [root port catalog](../README.md#local-profiles-and-ports).

## Related Modules

- [gateway](../gateway) is the fuller reactive gateway sample in this repo.
- [jacoco-report-aggregate](../jacoco-report-aggregate) includes this module in the aggregate coverage report.

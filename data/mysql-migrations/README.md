# mysql-migrations

This module demonstrates how to evolve a MySQL schema from `TIMESTAMP` storage to `DATETIME`, including existing data and compatibility across multiple MySQL versions.

## What It Demonstrates

- Flyway-driven schema migrations
- Spring Data JPA against MySQL
- Validation-oriented Hibernate settings during startup
- Local development with MySQL through `compose.yaml`

The migration scripts live under `src/main/resources/db/migration`.

## Run Locally

```bash
./mvnw -pl mysql-migrations spring-boot:run -Dspring-boot.run.profiles=local
```

The current local port defaults for this module and its supporting services are tracked in the [root port catalog](../README.md#local-profiles-and-ports).

## Related Modules

- [newman](../newman) contains a Postman collection for this sample.
- [jacoco-report-aggregate](../jacoco-report-aggregate) includes this module in the aggregate coverage report.

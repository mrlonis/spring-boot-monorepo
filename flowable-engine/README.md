# flowable-engine

This module is a Spring Boot sample that wires Flowable Engine into a typical application stack with Liquibase, MySQL, and Actuator.

## What It Demonstrates

- Flowable Engine bootstrapped with `flowable-spring-boot-starter`
- Liquibase-managed schema changes
- Spring Data JDBC and server-side templating support
- Local development with MySQL through `compose.yaml`

## Run Locally

```bash
./mvnw -pl flowable-engine spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile starts the app on `http://localhost:5367` and enables Docker Compose support for MySQL on `localhost:3307`.

## Related Modules

- [jacoco-report-aggregate](../jacoco-report-aggregate) includes this module in the repo-wide coverage report.
- [newman](../newman) contains a Postman collection for this app.

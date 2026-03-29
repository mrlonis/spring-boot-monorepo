# todo-app

This module is a todo service sample backed by PostgreSQL, Flyway, and Spring Data JPA with a reactive HTTP layer.

## What It Demonstrates

- CRUD-style todo endpoints exposed through WebFlux controllers
- Flyway-managed PostgreSQL schema migrations
- Spring Data JPA repositories and mapping code behind the API
- Lightweight metadata endpoints for PIs and sprints

The main endpoints today are:

- `GET /api/todo/items`
- `POST /api/todo/item`
- `GET /api/todo/itemsByPi`
- `GET /api/todo/itemsByPiAndBySprint`
- `GET /api/metadata/pis`
- `GET /api/metadata/sprints`

The controllers currently allow cross-origin requests from the local UI origin listed in the [root port catalog](../../README.md#local-profiles-and-ports), which makes the module easy to pair with a local Angular UI.

## Run Locally

```bash
./mvnw -pl todo-app spring-boot:run -Dspring-boot.run.profiles=local
```

The current local port defaults for this module and its supporting services are tracked in the [root port catalog](../../README.md#local-profiles-and-ports).

## Related Modules

- [newman](../../newman) includes a Postman collection for this service.
- [jacoco-report-aggregate](../../jacoco-report-aggregate) includes this module in the aggregate coverage report.

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

The controllers currently allow cross-origin requests from `http://localhost:4200`, which makes the module easy to pair with a local Angular UI.

## Run Locally

```bash
./mvnw -pl todo-app spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile runs on `http://localhost:6958` and expects PostgreSQL on `localhost:5433`. The included `compose.yaml` is set up for that local database.

## Related Modules

- [newman](../newman) includes a Postman collection for this service.
- [jacoco-report-aggregate](../jacoco-report-aggregate) includes this module in the aggregate coverage report.

# spring-boot-monorepo

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6f41092b9a6d420b87da42d29650f7c3)](https://app.codacy.com/gh/mrlonis/spring-boot-monorepo/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/6f41092b9a6d420b87da42d29650f7c3)](https://app.codacy.com/gh/mrlonis/spring-boot-monorepo/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)

This repository is a Spring Boot monorepo of runnable sample applications, shared auto-configuration modules, and supporting tooling. The modules are intentionally small and focused, which makes the repo useful as both a sandbox for framework experiments and a place to keep reusable starter code.

## What Is In Here

### Security and gateway samples

- [gateway](apps/gateway) - Reactive Spring Cloud Gateway sample with Redis-backed sessions, OIDC login, and custom gateway filters.
- [oauth2-authorization-server](apps/oauth2-authorization-server) - Spring Authorization Server sample that issues JWTs and supports OIDC login.
- [oauth2-authorization-server-opaque](apps/oauth2-authorization-server-opaque) - Authorization Server variant configured around opaque tokens and introspection.
- [oauth2-gateway-mvc](apps/oauth2-gateway-mvc) - Minimal Spring Cloud Gateway Server MVC sample.
- [spring-security](apps/spring-security) - Servlet resource server sample using JWT-based federation.
- [spring-security-opaque](apps/spring-security-opaque) - Servlet resource server sample using opaque token federation.
- [spring-security-reactive](apps/spring-security-reactive) - Reactive resource server sample using JWT-based federation.
- [spring-security-reactive-opaque](apps/spring-security-reactive-opaque) - Reactive resource server sample using opaque token federation.
- [oauth2-autoconfig](starters/oauth2-autoconfig) - Shared servlet/reactive security auto-configuration used by the security modules.
- [test-controller-autoconfig](starters/test-controller-autoconfig) - Tiny auto-configured `/v1/test` controller used by multiple integration tests.

### XML and Jackson samples

- [shared-jackson](libs/shared-jackson) - Shared XML controller, shared model matrix, and common Jackson configuration.
- [shared-jackson-test](testing/shared-jackson-test) - Reusable integration-test base classes for the XML samples.
- [jaxb](experiments/jaxb) - XML sample using `javax.xml.bind` annotations with Jackson support.
- [jakarta](experiments/jakarta) - XML sample using `jakarta.xml.bind` annotations with Jackson support.
- [pure-jaxb](experiments/pure-jaxb) - XML sample focused on the pure JAXB model family.
- [pure-jakarta](experiments/pure-jakarta) - XML sample focused on the pure Jakarta XML Bind model family.
- [jackson-jackson](experiments/jackson-jackson) - XML sample using native Jackson XML annotations.
- [jackson-jaxb](experiments/jackson-jaxb) - XML sample using Jackson with JAXB annotation integration.
- [jackson-jakarta](experiments/jackson-jakarta) - XML sample using Jackson with Jakarta XML Bind annotation integration.
- [jackson-all](experiments/jackson-all) - XML sample that registers both JAXB and Jakarta XML Bind Jackson modules together.

### Data, workflow, and tooling samples

- [todo-app](apps/todo-app) - Todo API backed by PostgreSQL, Flyway, and Spring Data JPA with a reactive web layer.
- [mysql-migrations](data/mysql-migrations) - Example of evolving a MySQL `TIMESTAMP` column to `DATETIME` across versions.
- [flowable-engine](apps/flowable-engine) - Flowable Engine sample backed by MySQL and Liquibase.
- [newman](newman) - Postman collections and environments for exercising the runnable modules.
- [pre-commit](pre-commit) - Maven module that installs git hooks and performs local-development setup work.
- [jacoco-report-aggregate](jacoco-report-aggregate) - Aggregates JaCoCo coverage across the repo for CI reporting.

## Prerequisites

- Java `25`
- Docker Desktop or another local Docker runtime
- A shell that can run the Maven wrapper

The repo includes `.nvmrc` because the Postman/Newman tooling can be run comfortably with Node.js as well, but Java and Docker are the main prerequisites for the Spring Boot modules.

## Common Commands

Build and test the full monorepo:

```bash
./mvnw verify
```

Run a single module:

```bash
./mvnw -pl spring-security spring-boot:run -Dspring-boot.run.profiles=local
```

Run tests for one module and its dependencies:

```bash
./mvnw -pl todo-app -am test
```

Install the repo's git hooks:

```bash
./mvnw -pl pre-commit initialize
```

Generate or refresh the aggregate coverage report:

```bash
./mvnw verify -pl jacoco-report-aggregate -am
```

## Local Profiles and Ports

Several runnable modules expose a `local` profile that turns on local ports and, in some cases, Docker Compose integration.

|                Module                | Local port |                         Local dependency                          |
|--------------------------------------|------------|-------------------------------------------------------------------|
| `flowable-engine`                    | `5367`     | MySQL on `3307`                                                   |
| `mysql-migrations`                   | `5368`     | MySQL on `3308`                                                   |
| `todo-app`                           | `6958`     | PostgreSQL on `5433`                                              |
| `spring-security`                    | `9054`     | JWT issuer from `oauth2-authorization-server`                     |
| `spring-security-opaque`             | `9055`     | Opaque introspection against `oauth2-authorization-server-opaque` |
| `gateway`                            | `9203`     | Redis plus OIDC issuer from `oauth2-authorization-server`         |
| `oauth2-gateway-mvc`                 | `9204`     | No custom routes by default                                       |
| `spring-security-reactive`           | `9373`     | JWT issuer from `oauth2-authorization-server`                     |
| `spring-security-reactive-opaque`    | `9378`     | Opaque introspection against `oauth2-authorization-server-opaque` |
| `oauth2-authorization-server`        | `9562`     | Optional local backing services defined in `compose.yaml`         |
| `oauth2-authorization-server-opaque` | `9563`     | Optional local backing services defined in `compose.yaml`         |

Notes:

- `gateway` and `oauth2-gateway-mvc` now use distinct default local ports (`9203` and `9204`), so you can run them side by side.
- The XML sample apps do not define dedicated local ports; they use the default Spring Boot port unless you override it.

## Port Management

Edit `ports/local-ports.json`, then refresh the port-managed files with:

```bash
node scripts/sync-ports.mjs
```

Check for drift without writing changes:

```bash
node scripts/sync-ports.mjs --check
```

The sync refreshes Spring local config, supporting-service host ports, Newman environments, the validation workflow, and the generated port table above.

## Module Conventions

- Shared infrastructure lives in dedicated modules such as [oauth2-autoconfig](starters/oauth2-autoconfig), [shared-jackson](libs/shared-jackson), and [test-controller-autoconfig](starters/test-controller-autoconfig).
- Runnable samples usually include focused integration tests that prove the scenario they demonstrate.
- Some modules ship a `compose.yaml` file and rely on Spring Boot's Docker Compose support when the `local` profile is enabled.
- The XML samples all expose the shared `/xml/**` endpoints from [shared-jackson](libs/shared-jackson), but each module varies the annotation strategy and Jackson module registration.

## Working Module-by-Module

Every Maven module in the root `pom.xml` now has a companion README. If you are starting from a specific area of the repo, jump into that module first:

- Security: [gateway](apps/gateway), [oauth2-authorization-server](apps/oauth2-authorization-server), [oauth2-authorization-server-opaque](apps/oauth2-authorization-server-opaque), [spring-security](apps/spring-security)
- XML: [shared-jackson](libs/shared-jackson), [jaxb](experiments/jaxb), [jakarta](experiments/jakarta), [jackson-jackson](experiments/jackson-jackson)
- Data/workflow: [todo-app](apps/todo-app), [mysql-migrations](data/mysql-migrations), [flowable-engine](apps/flowable-engine)
- Tooling: [newman](newman), [pre-commit](pre-commit), [jacoco-report-aggregate](jacoco-report-aggregate)

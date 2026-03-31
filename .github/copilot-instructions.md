# GitHub Copilot Instructions for `spring-boot-monorepo`

Use these instructions when suggesting code, docs, tests, or workflow changes in this repository.

## Repo Purpose

This repository is a Maven multi-module Spring Boot monorepo of:

- runnable sample applications
- shared libraries and starters
- reusable test-support modules
- templates for new apps
- repo tooling such as Newman collections, git hooks, coverage aggregation, and port-sync automation

The modules are intentionally small and focused. Treat the repo as a collection of examples and reusable building blocks, not as one large application with a single architecture.

Before making changes, read:

1. the root [README](../README.md)
2. the nearest module `README.md`
3. the target module `pom.xml` if the task affects dependencies, packaging, or plugins

## Technology Baseline

- Java version: `25`
- Spring Boot version: `4.0.5`
- Spring Cloud version: `2025.1.1`
- Build tool: Maven, via the Maven wrapper `./mvnw`
- Optional Node.js tooling: `.nvmrc` currently pins `v24.14.1` for Newman and port-sync tooling
- Docker/Testcontainers are normal parts of local development and CI for several modules

Do not introduce Gradle or alternate build systems unless explicitly requested.

## Repository Map

The root `pom.xml` is an aggregator for these top-level areas:

|            Path            |                                     Purpose                                      |
|----------------------------|----------------------------------------------------------------------------------|
| `apps/`                    | Runnable Spring Boot applications and sample services                            |
| `data/`                    | Data-centric sample applications                                                 |
| `experiments/`             | XML/Jackson experiment apps that compare annotation and serialization strategies |
| `libs/`                    | Shared libraries used by multiple modules                                        |
| `starters/`                | Reusable Spring Boot starter and auto-configuration modules                      |
| `templates/`               | Starter templates intended to be copied and customized                           |
| `testing/`                 | Reusable test support shared across modules                                      |
| `newman/`                  | Postman collections and local environment files for smoke/integration testing    |
| `ports/`                   | Source of truth for local app and supporting-service ports                       |
| `scripts/`                 | Repo automation scripts such as port synchronization                             |
| `pre-commit/`              | Local-development setup and git-hook installation module                         |
| `jacoco-report-aggregate/` | Aggregate coverage reporting for the monorepo                                    |
| `.github/`                 | GitHub Actions workflows and composite actions                                   |

### `apps/`

Runnable application modules:

- `flowable-engine` - Flowable Engine sample backed by MySQL and Liquibase
- `gateway` - reactive Spring Cloud Gateway sample with Redis-backed sessions and OIDC login
- `oauth2-authorization-server` - JWT-focused Spring Authorization Server sample
- `oauth2-authorization-server-opaque` - opaque-token authorization-server sample
- `oauth2-gateway-mvc` - Spring Cloud Gateway Server MVC sample
- `spring-security` - servlet resource server sample using JWT federation
- `spring-security-opaque` - servlet resource server sample using opaque token federation
- `spring-security-reactive` - reactive resource server sample using JWT federation
- `spring-security-reactive-opaque` - reactive resource server sample using opaque token federation
- `todo-app` - todo API backed by PostgreSQL, Flyway, Spring Data JPA, and a reactive HTTP layer

### `data/`

- `mysql-migrations` - sample of evolving a MySQL `TIMESTAMP` column to `DATETIME`

### `experiments/`

XML/Jackson experiment modules:

- `jaxb`
- `jakarta`
- `pure-jaxb`
- `pure-jakarta`
- `jackson-jackson`
- `jackson-jaxb`
- `jackson-jakarta`
- `jackson-all`

These modules intentionally compare different annotation families and Jackson module combinations. The differences between them are the point.

### `libs/`

- `shared-jackson` - shared XML controller, shared model matrix, and common Jackson configuration

### `starters/`

- `oauth2-autoconfig` - reusable servlet/reactive security auto-configuration
- `test-controller-autoconfig` - shared `/v1/test` controller used by integration tests

### `testing/`

- `shared-jackson-test` - reusable integration-test support for the XML experiment modules

### `templates/`

- `template-servlet` - template for creating a new servlet-based Spring Boot application

## How Copilot Should Work in This Repo

- Prefer targeted, module-scoped changes over repo-wide refactors.
- Keep each runnable sample understandable on its own.
- Reuse existing shared modules instead of copying code between apps.
- When a task is specific to one module, avoid touching unrelated sibling modules.
- Follow the architecture already demonstrated by the target module instead of imposing a new pattern.
- If behavior changes, update tests and docs in the same change when reasonable.

## Module Boundary Rules

- `apps/*` and `data/*` are runnable samples. Keep them runnable and focused.
- `libs/*` is for shared runtime code used by multiple modules.
- `starters/*` is for reusable auto-configuration and shared Spring Boot wiring.
- `testing/*` is for reusable test helpers and shared test fixtures.
- `templates/*` should remain generic starting points, not become domain-specific apps.
- Avoid direct dependencies from one runnable app to another. If code is shared, move it into `libs`, `starters`, or `testing` as appropriate.

Only promote code to a shared module when it is genuinely used by multiple modules. Do not create artificial abstractions just because two classes look similar.

## Important Repo-Specific Guardrails

### Security sample distinctions matter

Do not collapse or blur the distinctions between:

- servlet vs reactive stacks
- JWT vs opaque token federation
- authorization server vs gateway vs resource server responsibilities

Examples:

- `apps/gateway` is a reactive WebFlux gateway
- `apps/oauth2-gateway-mvc` is the MVC gateway sample
- `apps/spring-security*` modules are resource-server samples
- `apps/oauth2-authorization-server*` modules are identity-provider samples

### XML experiment differences are intentional

Do not "simplify" the XML experiment modules by converting everything to the same approach. This repository intentionally preserves differences across:

- `javax.xml.bind`
- `jakarta.xml.bind`
- Jackson XML annotations
- mixed Jackson/JAXB or Jackson/Jakarta integrations

If you change shared XML behavior, keep the experiment matrix intact and update the relevant shared tests.

### `todo-app` is intentionally mixed-stack

`apps/todo-app` uses:

- a reactive HTTP layer
- Spring Data JPA
- Flyway
- PostgreSQL

Do not replace it with R2DBC or try to make it "fully reactive" unless the task explicitly asks for that architectural change.

## Source Layout Conventions

Most modules follow standard Maven layout:

- `src/main/java`
- `src/main/resources`
- `src/test/java`
- `src/test/resources`

Other recurring patterns:

- static sample assets live in `src/main/resources/static`
- application configuration lives in `src/main/resources/application.yml`
- test overrides often live in `src/test/resources/application-test.yml`
- Flyway migrations live under `src/main/resources/db/migration`
- some modules include `compose.yaml` for local dependencies

Keep new files in the established package and folder structure of the target module.

## Java, Spring, and Configuration Conventions

- Stay compatible with Java `25` and Spring Boot `4.0.5`.
- Prefer modern Spring Boot configuration patterns already used in the repo.
- Prefer `@ConfigurationProperties` for structured configuration over scattered `@Value` usage.
- Keep ports, URLs, and environment-specific values in configuration, not hard-coded in Java classes.
- Match the existing stack in the target module: servlet stays servlet, reactive stays reactive.
- Use `com.mrlonis...` package names and mirror the module's existing package structure.

When working in starter modules:

- register auto-configuration in `META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
- if a module already includes `spring.factories` for compatibility, preserve the existing behavior unless the task explicitly changes it

When using Lombok, stay compatible with the repo-level `lombok.config` rather than introducing conflicting local Lombok settings.

## Build, Dependency, and Formatting Conventions

- Use `./mvnw` for Maven commands.
- Treat the root `pom.xml` as the source of truth for shared versions and common build behavior.
- Prefer dependency management from the root POM over repeating versions in child modules.
- Add dependencies to the narrowest module possible.
- Only add shared version properties to the root when the version is genuinely cross-module.
- Keep POM structure compatible with Spotless/SortPom ordering.

Formatting is enforced with Spotless:

- Java uses Palantir Java Format
- Markdown is formatted through Spotless/Flexmark
- YAML, JSON, HTML, CSS, XML, MJS, and SQL files are formatted through Spotless/Prettier
- POM files are sorted by Spotless/SortPom rules

Do not hand-format files in ways that fight the configured formatter.

## Testing Conventions

The repo uses both Surefire and Failsafe conventions across modules.

- use `*Tests.java` for unit or slice tests
- use `*IT.java` for integration tests
- prefer focused tests that prove the scenario the sample module exists to demonstrate

Common patterns already in use:

- `@SpringBootTest` for module integration tests
- `TestcontainersConfiguration` for DB-backed or service-backed integration tests
- `AbstractMockWebServerIT` for HTTP/security flows that stub remote services
- `application-test.yml` for test-only configuration

Before creating new test infrastructure, check whether the module already has helpers you can extend or reuse.

If you change an HTTP contract or sample behavior that is already covered by Postman/Newman, update the matching collection or environment files too.

## Local Profiles, Ports, and Generated Files

Several runnable modules support a `local` profile. Ports are centrally managed.

`ports/local-ports.json` is the source of truth for:

- local application ports
- local supporting-service ports

If you change ports or local-service wiring:

1. update `ports/local-ports.json`
2. run `node scripts/sync-ports.mjs`
3. commit the generated updates together

Do not hand-edit port-managed values in synced files unless you are also updating the source manifest or intentionally fixing the sync script.

The sync process updates files such as:

- `README.md` port table
- `newman/*-local.postman_environment.json`
- `.github/workflows/validate.yml`
- affected `application.yml` files and related local settings

Use `node scripts/sync-ports.mjs --check` to verify no drift remains.

## CI and Workflow Expectations

The main validation workflow:

- runs Maven verification across the monorepo
- checks that port-managed files are in sync
- submits the Maven dependency graph
- runs Newman-backed integration coverage for key runnable modules

When changing GitHub Actions:

- prefer reusing the existing composite actions in `.github/actions/`
- keep workflow logic consistent with the current validation structure
- avoid duplicating setup steps that already exist in shared actions

## Commands Copilot Should Prefer

Build and test the full repo:

```bash
./mvnw verify
```

CI-like validation:

```bash
./mvnw -B clean verify -fae -T 1C
```

Run tests for one module and required dependencies:

```bash
./mvnw -pl <module-artifactId-or-path> -am test
```

Run one module's full verification, including integration tests where configured:

```bash
./mvnw -pl <module-artifactId-or-path> -am verify
```

Run a single module locally with the `local` profile:

```bash
./mvnw -pl <module-artifactId-or-path> spring-boot:run -Dspring-boot.run.profiles=local
```

Install git hooks and local setup:

```bash
./mvnw -pl pre-commit initialize
```

Refresh aggregate coverage:

```bash
./mvnw verify -pl jacoco-report-aggregate -am
```

Refresh port-managed files:

```bash
node scripts/sync-ports.mjs
```

Check for port drift only:

```bash
node scripts/sync-ports.mjs --check
```

Run a Newman collection manually:

```bash
npx newman run newman/<collection>.postman_collection.json -e newman/<environment>-local.postman_environment.json
```

## Documentation Expectations

- Each Maven module should have a companion `README.md`.
- If you add or rename a module, update:
  - the root `README.md`
  - the relevant top-level folder `README.md`
  - the relevant aggregator `pom.xml`
- Keep examples and commands runnable.
- If behavior changes, update docs close to the affected module.

## What to Avoid

- Do not add Gradle files or alternate build instructions unless asked.
- Do not edit `target/`, IDE metadata, or generated build output.
- Do not introduce cross-app coupling where a shared `libs`, `starters`, or `testing` module is more appropriate.
- Do not flatten deliberate differences between reactive/servlet, JWT/opaque, or JAXB/Jakarta/Jackson sample modules.
- Do not change Java, Spring Boot, or Spring Cloud versions unless the task is explicitly a version upgrade.
- Do not hand-maintain synced port artifacts when `ports/local-ports.json` and `scripts/sync-ports.mjs` are the real source of truth.

## Default Decision Rule

If there is any ambiguity, prefer the smallest change that:

1. matches the target module's current patterns
2. keeps sample boundaries intact
3. preserves the purpose of the demo
4. keeps tests, local tooling, and docs aligned

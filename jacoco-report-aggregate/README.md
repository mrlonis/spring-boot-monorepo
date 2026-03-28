# jacoco-report-aggregate

This module is the coverage aggregation step for the monorepo. It depends on the runnable and shared modules that matter for CI coverage and produces the final JaCoCo report.

## What It Does

- Collects execution data from the sample apps and shared modules
- Produces the aggregated JaCoCo report used by CI and coverage tooling
- Gives the repo one place to reason about overall test coverage

## Run It

```bash
./mvnw verify -pl jacoco-report-aggregate -am
```

In normal day-to-day work you usually get this automatically from a full `./mvnw verify`.

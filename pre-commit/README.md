# pre-commit

This module manages local developer setup tasks that do not belong in the application modules themselves.

## What It Does

- Installs the repo's `pre-commit` and `post-commit` git hooks through `git-build-hook-maven-plugin`
- Applies the repo's Spotless configuration
- Runs `git submodule update --init --remote --force` during the `local-development` profile

## Run It

```bash
./mvnw -pl pre-commit initialize
```

Run this after cloning the repo or whenever you want to refresh the local hook installation.

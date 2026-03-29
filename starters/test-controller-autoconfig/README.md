# test-controller-autoconfig

This module is a tiny shared test helper that auto-configures a simple HTTP endpoint for other modules.

## What It Provides

- `ControllerAutoConfig`, an auto-configuration entry point
- `ExampleController`, which exposes `GET /v1/test`
- A stable endpoint for the security and gateway modules to secure and exercise in tests

The controller returns a plain `"Hello World!"`, which keeps the dependent tests focused on security and routing concerns instead of business logic.

## Related Modules

- [spring-security](../../apps/spring-security), [spring-security-reactive](../../apps/spring-security-reactive), [spring-security-opaque](../../apps/spring-security-opaque), and [spring-security-reactive-opaque](../../apps/spring-security-reactive-opaque) all rely on this endpoint in their integration tests.

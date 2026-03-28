# jackson-all

This module is the broadest XML sample in the repo. It registers both the JAXB and Jakarta XML Bind Jackson modules so the shared XML controller can exercise multiple annotation styles in one app.

## What It Demonstrates

- Spring Boot XML serialization through the shared `/xml/**` endpoints
- Simultaneous registration of `JaxbAnnotationModule` and `JakartaXmlBindAnnotationModule`
- Comparison-friendly setup for JAXB and Jakarta-annotated model families

## Run Locally

```bash
./mvnw -pl jackson-all spring-boot:run
```

The app uses the default Spring Boot port unless you override `server.port`. A good smoke test is:

```text
GET /xml/JAKARTA?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [shared-jackson](../shared-jackson) provides the controller, shared models, and common config.
- [shared-jackson-test](../shared-jackson-test) provides the reusable XML integration tests.

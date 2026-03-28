# jackson-jakarta

This module is a Jackson XML sample that registers the Jakarta XML Bind annotation module and exercises the `JACKSON_JAKARTA` model family.

## What It Demonstrates

- Jackson XML serialization with Jakarta XML Bind annotation support
- The shared `/xml/**` controller and shared test matrix
- A focused comparison point against the JAXB and Jackson-only variants

## Run Locally

```bash
./mvnw -pl jackson-jakarta spring-boot:run
```

Try:

```text
GET /xml/JACKSON_JAKARTA?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [shared-jackson](../shared-jackson) provides the shared controller and models.
- [jackson-all](../jackson-all) registers both JAXB and Jakarta XML Bind modules in one app.

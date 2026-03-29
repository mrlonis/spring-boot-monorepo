# jaxb

This module is an XML sample app centered on `javax.xml.bind` annotations.

## What It Demonstrates

- Shared `/xml/**` endpoints from [shared-jackson](../shared-jackson)
- Jackson support for JAXB annotations through `JaxbAnnotationModule`
- The `JAXB` model family across different accessor styles and date/time combinations

## Run Locally

```bash
./mvnw -pl jaxb spring-boot:run
```

Try:

```text
GET /xml/JAXB?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jakarta](../jakarta) is the parallel sample using `jakarta.xml.bind`.
- [shared-jackson-test](../shared-jackson-test) provides the reusable XML integration tests.

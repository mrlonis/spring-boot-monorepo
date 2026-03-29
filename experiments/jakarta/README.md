# jakarta

This module is an XML sample app centered on `jakarta.xml.bind` annotations.

## What It Demonstrates

- Shared `/xml/**` endpoints from [shared-jackson](../shared-jackson)
- Jackson support for Jakarta XML Bind annotations through `JakartaXmlBindAnnotationModule`
- The `JAKARTA` model family across different accessor styles and date/time combinations

## Run Locally

```bash
./mvnw -pl jakarta spring-boot:run
```

Try:

```text
GET /xml/JAKARTA?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jaxb](../jaxb) is the parallel sample using `javax.xml.bind`.
- [shared-jackson-test](../shared-jackson-test) provides the reusable XML integration tests.

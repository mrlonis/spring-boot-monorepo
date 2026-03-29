# jackson-jaxb

This module is a Jackson XML sample that registers the JAXB annotation module and exercises the `JACKSON_JAXB` model family.

## What It Demonstrates

- Jackson XML serialization with `javax.xml.bind` annotation support
- The shared `/xml/**` controller from [shared-jackson](../../libs/shared-jackson)
- A focused sample for comparing JAXB-based XML Bind integration to the Jakarta and Jackson-only variants

## Run Locally

```bash
./mvnw -pl jackson-jaxb spring-boot:run
```

Try:

```text
GET /xml/JACKSON_JAXB?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jaxb](../jaxb) shows the non-Jackson-prefixed JAXB sample family.
- [shared-jackson-test](../../testing/shared-jackson-test) provides the reusable integration test base classes.

# jackson-jackson

This module is the XML sample that leans on native Jackson XML annotations rather than JAXB or Jakarta XML Bind annotations.

## What It Demonstrates

- The shared `/xml/**` controller from [shared-jackson](../../libs/shared-jackson)
- Jackson XML serialization with the `JACKSON_JACKSON` model family
- The baseline Jackson-only variant used for comparison with the JAXB and Jakarta samples

## Run Locally

```bash
./mvnw -pl jackson-jackson spring-boot:run
```

Try:

```text
GET /xml/JACKSON_JACKSON?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jackson-jaxb](../jackson-jaxb) and [jackson-jakarta](../jackson-jakarta) add XML Bind annotation support on top of similar sample flows.
- [shared-jackson-test](../../testing/shared-jackson-test) contains the shared test scaffolding.

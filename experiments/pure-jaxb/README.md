# pure-jaxb

This module is an XML sample focused on the pure JAXB model family.

## What It Demonstrates

- The shared `/xml/**` controller from [shared-jackson](../../libs/shared-jackson)
- Jackson support for `javax.xml.bind` annotations
- The `PURE_JAXB` model family from the shared model matrix

This module is useful when you want the JAXB-focused sample without the broader mixed-annotation comparisons.

## Run Locally

```bash
./mvnw -pl pure-jaxb spring-boot:run
```

Try:

```text
GET /xml/PURE_JAXB?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jaxb](../jaxb) covers the non-`PURE_` JAXB family.
- [shared-jackson-test](../../testing/shared-jackson-test) supplies the reusable integration test base classes.

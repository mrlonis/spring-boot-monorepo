# pure-jakarta

This module is an XML sample focused on the pure Jakarta XML Bind model family.

## What It Demonstrates

- The shared `/xml/**` controller from [shared-jackson](../../libs/shared-jackson)
- Jackson support for `jakarta.xml.bind` annotations
- The `PURE_JAKARTA` model family from the shared model matrix

This module is useful when you want the Jakarta-focused sample without pulling in the broader mixed-annotation comparisons.

## Run Locally

```bash
./mvnw -pl pure-jakarta spring-boot:run
```

Try:

```text
GET /xml/PURE_JAKARTA?accessType=FIELD&dateLibrary=JAVA_TIME&zoned=ZONED
```

## Related Modules

- [jakarta](../jakarta) covers the non-`PURE_` Jakarta family.
- [shared-jackson-test](../../testing/shared-jackson-test) supplies the reusable integration test base classes.

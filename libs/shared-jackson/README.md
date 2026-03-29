# shared-jackson

This module is the shared XML foundation for the repo's annotation and serialization experiments.

## What It Provides

- The shared `XmlController` exposed at `/xml/**`
- A cached model lookup utility that maps annotation family, accessor type, time library, and zoned/non-zoned variants
- Shared Jackson auto-configuration, including `JodaModule`
- Shared model definitions used by the XML sample apps

The key endpoints are:

- `GET /xml/{formatLibrary}` to fetch a sample model
- `POST /xml/deserialize` to round-trip JSON or XML back through the model layer

## How It Is Used

You do not usually run this module by itself. It is consumed by the XML sample applications such as [jaxb](../jaxb), [jakarta](../jakarta), [pure-jaxb](../pure-jaxb), and the `jackson-*` modules.

## Related Modules

- [shared-jackson-test](../shared-jackson-test) contains the reusable integration tests for these endpoints.
- [jackson-all](../jackson-all) and the other XML app modules provide runnable entry points around this shared code.

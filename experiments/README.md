# experiments

This folder groups the XML and Jackson experiment applications in the monorepo.

## Modules

- [jackson-all](jackson-all) - Registers both JAXB and Jakarta XML Bind Jackson modules
- [jackson-jackson](jackson-jackson) - Native Jackson XML annotation sample
- [jackson-jakarta](jackson-jakarta) - Jackson XML with Jakarta XML Bind annotation support
- [jackson-jaxb](jackson-jaxb) - Jackson XML with JAXB annotation support
- [jakarta](jakarta) - Jakarta XML Bind sample
- [jaxb](jaxb) - JAXB sample
- [pure-jakarta](pure-jakarta) - Pure Jakarta XML Bind model sample
- [pure-jaxb](pure-jaxb) - Pure JAXB model sample

These apps share infrastructure from [libs/shared-jackson](../libs/shared-jackson) and [testing/shared-jackson-test](../testing/shared-jackson-test).

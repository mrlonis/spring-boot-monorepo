# shared-jackson-test

This module contains reusable test support for the XML sample applications.

## What It Provides

- Base integration tests for the shared `/xml/**` controller behavior
- Shared constants and helper utilities for XML and JSON assertions
- A single place to keep the XML sample test matrix consistent across modules

## How It Is Used

Modules such as [jaxb](../jaxb), [jakarta](../jakarta), [pure-jaxb](../pure-jaxb), [pure-jakarta](../pure-jakarta), and the `jackson-*` samples depend on this module in test scope.

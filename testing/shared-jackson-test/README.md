# shared-jackson-test

This module contains reusable test support for the XML sample applications.

## What It Provides

- Base integration tests for the shared `/xml/**` controller behavior
- Shared constants and helper utilities for XML and JSON assertions
- A single place to keep the XML sample test matrix consistent across modules

## How It Is Used

Modules such as [jaxb](../../experiments/jaxb), [jakarta](../../experiments/jakarta), [pure-jaxb](../../experiments/pure-jaxb), [pure-jakarta](../../experiments/pure-jakarta), and the `jackson-*` samples depend on this module in test scope.

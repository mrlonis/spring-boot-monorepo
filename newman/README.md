# newman

This folder contains the Postman collections and local environments for the runnable modules in this repository.

## What Is Here

- One collection per sample app where HTTP coverage is helpful
- Matching `*-local.postman_environment.json` files for local development
- A quick way to smoke-test modules without writing new scripts

## How To Use It

You can import the collections into Postman, or run them with Newman. For example:

```bash
npx newman run newman/gateway.postman_collection.json -e newman/gateway-local.postman_environment.json
```

Pick the collection and environment that match the module you are exercising.

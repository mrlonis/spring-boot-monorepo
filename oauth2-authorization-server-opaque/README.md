# oauth2-authorization-server-opaque

## Table of Contents

<!-- TOC -->
* [oauth2-authorization-server-opaque](#oauth2-authorization-server-opaque)
  * [Table of Contents](#table-of-contents)
  * [Overview](#overview)
  * [Demonstrated Security Patterns](#demonstrated-security-patterns)
    * [Token-Based Authentication](#token-based-authentication)
      * [Opaque Tokens](#opaque-tokens)
    * [Session-Based Authentication](#session-based-authentication)
    * [Basic Authentication](#basic-authentication)
    * [Form-Based Authentication](#form-based-authentication)
    * [OAuth2 and OpenID Connect (OIDC)](#oauth2-and-openid-connect-oidc)

<!-- TOC -->

## Overview

This project is a simple OAuth2 Authorization Server built with Spring Boot. It provides authorization capabilities to
the other Spring Boot applications found in this monorepo

## Demonstrated Security Patterns

### Token-Based Authentication

#### Opaque Tokens

- Reference token, not self-contained.
- Requires introspection endpoint to validate.
- Often used in OAuth2 with introspection (especially with Spring Authorization Server).

### Session-Based Authentication

Handled in the [gateway](../gateway) module. The gateway uses Spring Security and Spring Session with Redis to manage
user sessions.

- Traditional approach for web apps.
- After login, server creates a session (typically stored in memory or a session store).
- A JSESSIONID cookie is sent to the client and used on future requests.

Use Cases:

- Monolithic MVC apps (e.g., with Thymeleaf).
- Admin dashboards and internal tools.

### Basic Authentication

The client id and client secrets for the authentication flows are sent as basic headers, along with your usual
scopes and grant types in the request body.

- HTTP header-based: sends Authorization: Basic base64(username:password).
- Extremely simple and insecure unless over HTTPS.
- Not recommended for production but useful for quick internal APIs or dev tools.

### Form-Based Authentication

The authorization server provides a login page for the user to enter their credentials.

- Standard username/password login via HTML form.
- Spring Security handles form login, CSRF, redirection, etc.

Variants:

- Default Spring login page
- Custom login pages with form-based POST

### OAuth2 and OpenID Connect (OIDC)

- Delegated authentication via third parties (Google, GitHub, Okta, etc.)
- Spring Security has full support as both client and authorization server.

Grant Types:

- Authorization Code (with/without PKCE)
- Client Credentials
- Resource Owner Password Credentials (deprecated)
- Refresh Token

OIDC Layer adds:

- ID Token (a JWT)
- UserInfo endpoint
- Login/logout flows

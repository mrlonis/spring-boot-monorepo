package com.mrlonis.example.oauth2.model.enums;

public enum ClientAuthenticationMethods {
    CLIENT_SECRET_BASIC,
    CLIENT_SECRET_POST,
    CLIENT_SECRET_JWT,
    PRIVATE_KEY_JWT,
    NONE,
    TLS_CLIENT_AUTH,
    SELF_SIGNED_TLS_CLIENT_AUTH
}

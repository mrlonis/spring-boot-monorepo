package com.mrlonis.example.oauth2.opaque.model;

import lombok.Data;

@Data
public class ClientSettings {
    private boolean requireAuthorizationConsent;
    private boolean requireProofKey;
}

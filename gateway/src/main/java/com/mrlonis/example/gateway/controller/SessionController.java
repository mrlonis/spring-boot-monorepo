package com.mrlonis.example.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;

@RestController
@RequestMapping("/v1/session")
public class SessionController {
    @GetMapping(value = "/id", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getSessionId(WebSession webSession) {
        return webSession.getId();
    }
}

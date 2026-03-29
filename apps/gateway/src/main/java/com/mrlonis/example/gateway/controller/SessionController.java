package com.mrlonis.example.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/session")
public class SessionController {
    @GetMapping(value = "/id", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getSessionId(WebSession webSession) {
        webSession.getAttributes().put("touched", true);
        return Mono.just(webSession.getId());
    }
}

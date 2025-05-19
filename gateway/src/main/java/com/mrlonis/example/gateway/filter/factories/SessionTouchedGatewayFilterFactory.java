package com.mrlonis.example.gateway.filter.factories;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionTouchedGatewayFilterFactory
        extends AbstractGatewayFilterFactory<SessionTouchedGatewayFilterFactory.Config> {
    public SessionTouchedGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> exchange.getSession().flatMap(session -> {
            // Perform any operations with the session here
            // For example, you can set an attribute in the session
            session.getAttributes().put("sessionTouched", true);
            return chain.filter(exchange);
        });
    }

    @Data
    public static class Config {}
}

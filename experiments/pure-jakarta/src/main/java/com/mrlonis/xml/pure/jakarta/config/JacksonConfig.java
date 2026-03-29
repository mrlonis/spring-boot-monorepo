package com.mrlonis.xml.pure.jakarta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.JacksonModule;
import tools.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;

/**
 * This is a Jackson configuration class that registers additional Jackson modules not included in the default Spring
 * Boot autoconfiguration.
 */
@Configuration
public class JacksonConfig {
    /**
     * Registers the JakartaXmlBindAnnotationModule for Spring Boot. This module is used to add support for the Jakarta
     * annotations (jakarta.xml.bind.annotation.*).
     *
     * @return {@link JakartaXmlBindAnnotationModule}
     */
    @Bean
    public JacksonModule jakartaXmlBindAnnotationModule() {
        return new JakartaXmlBindAnnotationModule();
    }
}

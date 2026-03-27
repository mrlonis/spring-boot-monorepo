package com.mrlonis.xml.jakarta.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Module jaxbAnnotationModule() {
        return new JakartaXmlBindAnnotationModule();
    }
}

package com.mrlonis.xml.jackson.jaxb.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This is a Jackson configuration class that registers additional Jackson modules not included in the default Spring
 * Boot autoconfiguration.
 */
@Configuration
public class JacksonConfig {
    /**
     * Registers the JaxbAnnotationModule for Spring Boot. This module is used to add support for the JAXB annotations
     * (javax.xml.bind.annotation.*).
     *
     * @return {@link JaxbAnnotationModule}
     */
    @Bean
    public Module jaxbAnnotationModule() {
        return new JaxbAnnotationModule();
    }
}

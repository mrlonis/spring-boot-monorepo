package com.mrlonis.xml.jackson.all.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.JacksonModule;
import tools.jackson.module.jakarta.xmlbind.JakartaXmlBindAnnotationModule;
import tools.jackson.module.jaxb.JaxbAnnotationModule;

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
    public JacksonModule jaxbAnnotationModule() {
        return new JaxbAnnotationModule();
    }

    /**
     * Register the JakartaXmlBindAnnotationModule for Spring Boot. This module is used to add support for the Jakarta
     * XML Bind API annotations (jakarta.xml.bind.annotation.*).
     *
     * @return {@link JakartaXmlBindAnnotationModule}
     */
    @Bean
    public JacksonModule jakartaXmlBindAnnotationModule() {
        return new JakartaXmlBindAnnotationModule();
    }
}

package com.mrlonis.xml.shared.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.JacksonModule;
import tools.jackson.datatype.joda.JodaModule;

@AutoConfiguration
public class SharedJacksonConfig {
    @Bean
    public JacksonModule jodaModule() {
        return new JodaModule();
    }
}

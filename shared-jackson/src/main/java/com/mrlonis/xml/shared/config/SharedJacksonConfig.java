package com.mrlonis.xml.shared.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SharedJacksonConfig {
    @Bean
    public Module jodaModule() {
        return new JodaModule();
    }
}

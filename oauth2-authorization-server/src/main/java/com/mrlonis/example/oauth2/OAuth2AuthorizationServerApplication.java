package com.mrlonis.example.oauth2;

import com.mrlonis.example.oauth2.config.properties.ApplicationProperties;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties.class)
public class OAuth2AuthorizationServerApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(OAuth2AuthorizationServerApplication.class, args);
    }
}

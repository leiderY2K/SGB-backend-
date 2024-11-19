package org.example.sgb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.http.HttpClient;

@Configuration
public class WebConfig {

    @Bean
    @Scope("singleton") // Esto asegura que el mismo HttpClient sea reutilizado.
    public HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}

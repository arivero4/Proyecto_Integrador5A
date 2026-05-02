package com.example.predios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(jwtForwardInterceptor()));
        return restTemplate;
    }

    private ClientHttpRequestInterceptor jwtForwardInterceptor() {
        return (request, body, execution) -> {
            try {
                var attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                String authHeader = attrs.getRequest().getHeader("Authorization");
                if (authHeader != null && !request.getHeaders().containsKey("Authorization")) {
                    request.getHeaders().set("Authorization", authHeader);
                }
            } catch (Exception ignored) {
                // Sin contexto de request (ej: llamada asíncrona)
            }
            return execution.execute(request, body);
        };
    }
}

package org.example.taskmanagerbackend2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    public SecurityConfig() {
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOriginPatterns(new String[]{"http://localhost:*", "http://127.0.0.1:*"}).allowedMethods(new String[]{"GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"}).allowedHeaders(new String[]{"*"}).allowCredentials(true).maxAge(3600L);
    }
}


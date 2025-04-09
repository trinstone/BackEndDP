package com.example.dubinskoPranje.slojevi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSKonfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for the entire application
        registry.addMapping("/**")  // Apply CORS on all endpoints
                .allowedOrigins("http://localhost:3000")  // Frontend URL (React app)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Supported HTTP methods
                .allowedHeaders("*")  // Allow any headers
                .allowCredentials(true)  // Allow cookies and credentials to be sent
                .maxAge(3600);  // Cache CORS response for 1 hour to reduce preflight requests
    }
}


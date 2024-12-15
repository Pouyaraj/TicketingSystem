package com.example.TicketingSystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity (not recommended for production)
            .authorizeRequests()
                .requestMatchers("/register", "/login").permitAll() // Allow unauthenticated access to these endpoints
                .anyRequest().authenticated() // Require authentication for all other endpoints
            .and()
            .httpBasic(); // Enable Basic Authentication for other endpoints

        return http.build();
    }
}


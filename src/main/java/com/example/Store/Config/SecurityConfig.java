package com.example.Store.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

    private final AuthenticationSuccessHandler successHandler;

    public SecurityConfig(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**") // Disables CSRF for Postman testing
                )
                .authorizeHttpRequests(auth -> auth
                        // Admin-only endpoints
                        .requestMatchers(HttpMethod.POST, "/api/add/product").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/delete/product/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/product/update/price/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}


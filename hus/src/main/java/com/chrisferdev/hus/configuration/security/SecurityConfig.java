package com.chrisferdev.hus.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ROLE_ADMIN = "ADMIN";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf( csrf-> csrf.disable()).authorizeHttpRequests(
                aut -> aut.requestMatchers("/api/admin/categories/**").hasRole(ROLE_ADMIN)
                        .requestMatchers("/api/admin/brands/**").hasRole(ROLE_ADMIN)
                        .requestMatchers("/api/admin/products/**").hasRole(ROLE_ADMIN)
                        .requestMatchers("/api/admin/security/register/**").hasRole(ROLE_ADMIN)
                        .requestMatchers( "/api/security/login**").permitAll().anyRequest().authenticated()
        );

        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

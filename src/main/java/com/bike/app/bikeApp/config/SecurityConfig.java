package com.bike.app.bikeApp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/**").hasRole("USER").and().oauth2Login(oauth2 -> oauth2
                .defaultSuccessUrl("/", true)
                .userInfoEndpoint(userInfo -> userInfo
                        .userAuthoritiesMapper(authorities -> {
                            // Log the original authorities to debug
                            authorities.forEach(authority -> System.out.println("Authority: " + authority.getAuthority()));

                            // Map authorities to Spring Security roles
                            return authorities.stream()
                                    .map(grantedAuthority -> {
                                        if ("OAUTH2_USER".equals(grantedAuthority.getAuthority()) || "SCOPE_read:user".equals(grantedAuthority.getAuthority())) {
                                            // If the user has these authorities, map to ROLE_USER
                                            return new SimpleGrantedAuthority("ROLE_USER");
                                        }
                                        return grantedAuthority; // Keep other authorities as they are
                                    })
                                    .collect(Collectors.toList());
                        })
                )
        );


        return http.build();
    }
}

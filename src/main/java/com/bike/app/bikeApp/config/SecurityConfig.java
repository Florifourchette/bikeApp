package com.bike.app.bikeApp.config;

import com.bike.app.bikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Map;

@Configuration
public class SecurityConfig {

    private final UserService userService; // Declare UserService

    // Inject UserService via constructor
    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/", true)
                        .successHandler(customSuccessHandler())
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
        );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) -> {
            System.out.println("AuthenticationSuccessHandler reached");

            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                Map<String, Object> attributes = oauth2User.getAttributes();
                String name = (String) attributes.get("name");
                String username = (String) attributes.get("login");
                Integer id = (Integer) attributes.get("id");
                String user_name = (String) attributes.get("login");
                System.out.println("My username is: " + username);
                System.out.println("My id is: " + id);

                userService.saveUser(name);
            }

            // Redirect to home page after successful login
            response.sendRedirect("/");
        };
    }

}

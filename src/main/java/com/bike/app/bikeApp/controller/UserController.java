package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@SpringBootApplication
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public UUID user(@AuthenticationPrincipal OAuth2User principal, OAuth2AuthenticationToken authenticationToken) {
        String externalProviderId = "unknown";
        String login = "unknown";

        if(authenticationToken != null){
            externalProviderId = authenticationToken.getAuthorizedClientRegistrationId();
        }
        if("google".equalsIgnoreCase(externalProviderId)){
            login = principal.getAttribute("email");
            System.out.println("email is"+principal.getAttribute("login"));
        }
        if("github".equalsIgnoreCase(externalProviderId)){
            login = principal.getAttribute("login");
        }
        String name = principal.getAttribute("name");
        return userService.getUser(externalProviderId, name, login);
    }

    @GetMapping("/info")
    public Map<String, Object> getUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        String name = oidcUser.getAttribute("sub");

        System.out.println("User ID (sub): " + name);

        return oidcUser.getAttributes();
    }
}
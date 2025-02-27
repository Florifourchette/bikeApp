package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@SpringBootApplication
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User user(@AuthenticationPrincipal OAuth2User principal, OAuth2AuthenticationToken authenticationToken) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }

        String externalProviderId = "unknown";
        String login = "unknown";

        if (authenticationToken != null) {
            externalProviderId = authenticationToken.getAuthorizedClientRegistrationId();
        }

        if ("google".equalsIgnoreCase(externalProviderId)) {
            login = principal.getAttribute("email");
            System.out.println("email is " + login);
        }

        if ("github".equalsIgnoreCase(externalProviderId)) {
            login = principal.getAttribute("login");
        }

        String name = principal.getAttribute("name");

        try {
            return userService.getUser(externalProviderId, name, login);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching user", e);
        }
    }


    @PatchMapping("/user")
    public User getUserInfo(@RequestBody String attribute, @RequestBody String value) {

        return userService.updateUser(attribute, value);
    }
}
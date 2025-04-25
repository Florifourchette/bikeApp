package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.dto.UserDTO;
import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public User getUserInfo(@RequestBody UserDTO request) {
        System.out.println("User name in UserController is: " + request.getName());
        System.out.println("User email in UserController is: " + request.getEmail());
        return userService.updateUser(request);
    }
}
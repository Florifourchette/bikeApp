package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.dto.UserDTO;
import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UUID userId;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String externalProviderId,String name, String login) {
        Optional<User> existingUser = findExistingUser(externalProviderId, login);

        if (existingUser.isPresent()) {
            userId = existingUser.get().getId();
            return existingUser.get();
        }
        User user = new User();
        switch (externalProviderId.toLowerCase()) {
            case "github" -> {
                saveNewUserByUsername(user, login);
            }
            case "google" -> saveNewUserByEmail(user, login);
            default -> System.out.println("Unsupported provider: " + externalProviderId);
        }
        user.setName(name);
        user.setExternalProviderId(externalProviderId);
        userRepository.save(user);
        userId = userRepository.save(user).getId();
        System.out.println("in UserService, userId is: "+ userId);
        return userRepository.save(user);
    }

    public User updateUser(UserDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional.ofNullable(request.getName()) //check if name is not null
                .filter(StringUtils::hasText) //Ensure name is not empty or only spaces
                .ifPresent(user::setName); //Set name if valid

        Optional.ofNullable(request.getEmail())
                .filter(StringUtils::hasText)
                .ifPresent(user::setEmail);

        return userRepository.save(user);
    }

    private UUID saveNewUserByUsername(User newUser, String login){
            newUser.setUsername(login);
            userRepository.save(newUser);
            return newUser.getId();
    }

    private UUID saveNewUserByEmail(User newUser, String login){
            newUser.setEmail(login);
            userRepository.save(newUser);
            return newUser.getId();
    }
    private Optional<User> findExistingUser(String provider, String login) {
        return switch (provider.toLowerCase()) {
            case "github" -> userRepository.findByUsername(login);
            case "google" -> userRepository.findByEmail(login);
            default -> Optional.empty();
        };
}}

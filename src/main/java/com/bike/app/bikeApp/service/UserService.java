package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UUID getUser(String externalProviderId,String name, String login) {
        Optional<User> existingUser = findExistingUser(externalProviderId, login);

        if (existingUser.isPresent()) {
            return existingUser.get().getId();
        }
        User user = new User();
        System.out.println("login is: "+login);
        switch (externalProviderId.toLowerCase()) {
            case "github" -> {
                System.out.println("if GitHub reached and login is: " + login);
                saveNewUserByUsername(user, login);
            }
            case "google" -> saveNewUserByEmail(user, login);
            default -> System.out.println("Unsupported provider: " + externalProviderId);
        }
        user.setName(name);
        user.setExternalProviderId(externalProviderId);
        userRepository.save(user);
        userId = userRepository.save(user).getId();
        return userId;
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

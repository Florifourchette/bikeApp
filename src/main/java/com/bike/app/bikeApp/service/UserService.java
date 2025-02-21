package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UUID userId;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID saveUser(String externalProviderId,String name) {
        System.out.println("saveUser reached");
        User user = new User();
        user.setName(name);
        user.setExternalProviderId(externalProviderId);
        userRepository.save(user);
        userId = userRepository.save(user).getId();
        return userId;
    }
}

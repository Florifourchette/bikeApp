package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.dto.BikeDTO;
import com.bike.app.bikeApp.dto.UserDTO;
import com.bike.app.bikeApp.entity.Bike;
import com.bike.app.bikeApp.entity.User;
import com.bike.app.bikeApp.service.BikeService;
import com.bike.app.bikeApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bike")
public class BikeController {

    private final UserService userService;
    private final BikeService bikeService;

    @Autowired
    public BikeController(UserService userService, BikeService bikeService) {
        this.userService = userService;
        this.bikeService = bikeService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/all")
    public List<Bike> getAllBikes() {
        System.out.println("In BikeController, the userId is: "+userService.userId);
        return bikeService.getBikesByUserId();
    }

    @GetMapping("/{id}")
    public Bike getPerId(@PathVariable UUID id) {
        return bikeService.getPerId(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> saveName(@RequestBody Bike bike) {
        return bikeService.saveBike(bike);
    }
}

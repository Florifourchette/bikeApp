package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.dto.BikeDTO;
import com.bike.app.bikeApp.entity.Bike;
import com.bike.app.bikeApp.service.BikeService;
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

    @Autowired
    private BikeService bikeService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/all")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @GetMapping("/{id}")
    public Bike getPerId(@PathVariable UUID id) {
        return bikeService.getPerId(id);
    }

    @PostMapping("/")
    public Bike saveName(@RequestBody Bike bike) {
        System.out.println("bike purchase Date" + bike.getPurchasedDate());
        return bikeService.saveBike(bike);
    }
}

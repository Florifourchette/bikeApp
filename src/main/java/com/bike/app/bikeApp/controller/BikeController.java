package com.bike.app.bikeApp.controller;

import com.bike.app.bikeApp.dto.BikeDTO;
import com.bike.app.bikeApp.entity.Bike;
import com.bike.app.bikeApp.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/bike")
    public List<Bike> getAllBikes() {
        return bikeService.getAllBikes();
    }

    @PostMapping("/bike")
    public Bike saveName(@RequestBody Bike bike) {
        System.out.println(bike);
        return bikeService.saveBike(bike);
    }
}

package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.entity.Bike;
import com.bike.app.bikeApp.repository.BikeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;
    private final UserService userService;


    @Autowired
    public BikeService(UserService userService, BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
        this.userService = userService;
    }

    public ResponseEntity<?> saveBike(Bike newBike) {
        Bike bike = new Bike();
        if(userService.userId != null){
            System.out.println(userService.userId != null);
            bike.setName(newBike.getName());
            bike.setBrand(newBike.getBrand());
            bike.setModel(newBike.getModel());
            bike.setType(newBike.getType());
            bike.setElectric(newBike.getElectric());
            bike.setColour(newBike.getColour());
            bike.setPurchasedDate(newBike.getPurchasedDate());
            bike.setIsNew(newBike.getIsNew());
            bike.setDescription(newBike.getDescription()    );
            System.out.println("userId is: "+userService.userId);
            bike.setUserId(userService.userId);

            if (newBike.getNumberOfKm() != null && newBike.getNumberOfKm() > 0) {
                savedKm(bike, newBike.getNumberOfKm());
            } else {
                bike.setNumberOfKm(0);
            }
            return ResponseEntity.ok(bikeRepository.save(bike));
        }else{
            return ResponseEntity.status(401).body("User not authenticated. Please log in.");
        }
    }

    public void savedKm(Bike bike, Integer km) {
        bike.setNumberOfKm(km);
        bikeRepository.save(bike);
    }

    public List<Bike> getBikesByUserId() {
        System.out.println("User Id is: "+userService.userId);
        return bikeRepository.findByUserId(userService.userId);
    }

    public Bike getPerId(UUID id) {
        return bikeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Bike not found with ID: " + id));
    }
}

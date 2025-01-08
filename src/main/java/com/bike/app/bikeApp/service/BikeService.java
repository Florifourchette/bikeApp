package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.entity.Bike;
import com.bike.app.bikeApp.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike saveBike(Bike newBike) {
        Bike bike = new Bike();
        bike.setName(newBike.getName());
        bike.setBrand(newBike.getBrand());
        bike.setModel(newBike.getModel());
        bike.setColour(newBike.getColour());
        bike.setPurchasedDate(newBike.getPurchasedDate());
        bike.setNew(newBike.getNew());

        if(newBike.getNumberOfKm() != null && newBike.getNumberOfKm() > 0){
            savedKm(bike, newBike.getNumberOfKm());
        }
        else{
            bike.setNumberOfKm(0);
        }
        return bikeRepository.save(bike);
    }

    public void savedKm(Bike bike, Integer km){
        bike.setNumberOfKm(km);
        bikeRepository.save(bike);
    }

    public List<Bike> getAllBikes(){
      return bikeRepository.findAll();
    }
}

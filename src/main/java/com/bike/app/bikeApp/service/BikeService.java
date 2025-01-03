package com.bike.app.bikeApp.service;

import com.bike.app.bikeApp.dto.BikeDTO;
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

    public BikeDTO saveBike(String name) {
        BikeDTO bike = new BikeDTO();
        bike.setName(name);
        System.out.println("Saving bike: " + bike.getName());
        bikeRepository.save(bike);
        return bike;
    }

    public List<Bike> getAllBikes(){
      return bikeRepository.findAll();
    }
}

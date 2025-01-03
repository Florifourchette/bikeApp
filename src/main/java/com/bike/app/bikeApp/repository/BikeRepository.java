package com.bike.app.bikeApp.repository;

import com.bike.app.bikeApp.entity.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BikeRepository extends JpaRepository<Bike, UUID> {
}

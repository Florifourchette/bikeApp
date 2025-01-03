package com.bike.app.bikeApp.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

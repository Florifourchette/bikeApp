package com.bike.app.bikeApp.entity;

import com.bike.app.bikeApp.entity.enums.BikeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bike")
public class Bike {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Setter
    private UUID userId;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String brand;

    @Getter
    @Setter
    private String model;

    @Getter
    @Setter
    private BikeType type;

    @Getter
    @Setter
    private Boolean electric;

    @Getter
    @Setter
    private String colour;

    @Getter
    @Setter
    @Column(name = "purchased_date")
    private LocalDate purchasedDate;

    @Getter
    @Setter
    @Column(name = "number_of_km")
    private Integer numberOfKm;

    @Getter
    @Setter
    @Column(name = "is_new")
    private Boolean isNew;

    @Getter
    @Setter
    @Column(name = "image_url")
    private String imageUrl;

    @Getter
    @Setter
    private String description;


    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

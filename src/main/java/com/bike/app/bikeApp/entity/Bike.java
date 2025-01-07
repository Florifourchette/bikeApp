package com.bike.app.bikeApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="bike")
public class Bike {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String brand;

    private String model;

    private String colour;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "purchased_date")
    private Date purchasedDate;

    @Column(name = "number_of_km")
    private Integer numberOfKm;

    @Column(name = "is_new")
    private Boolean isNew;

    private String description;

    //getter und setter

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColour() {
        return colour;
    }


    public Integer getNumberOfKm() {
        return numberOfKm;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public void setPurchasedDate(Date purchaseDate) {
        this.purchasedDate = purchasedDate;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public void setNumberOfKm(Integer numberOfKm) {
        this.numberOfKm = numberOfKm;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}

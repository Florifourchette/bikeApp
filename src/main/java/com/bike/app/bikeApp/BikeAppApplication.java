package com.bike.app.bikeApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class BikeAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(
                BikeAppApplication.class, args);
    }
}
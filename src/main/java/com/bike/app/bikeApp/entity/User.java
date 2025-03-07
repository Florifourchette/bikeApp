package com.bike.app.bikeApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String externalProviderId;
}

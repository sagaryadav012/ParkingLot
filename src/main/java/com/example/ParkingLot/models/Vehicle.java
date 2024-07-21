package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "vehicles")
@Data
public class Vehicle {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String color;
    private String registerNumber;
    private VehicleType vehicleType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person person;
}

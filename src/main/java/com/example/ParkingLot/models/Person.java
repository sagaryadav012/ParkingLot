package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

@Entity(name = "persons")
@Data
public class Person {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    private String phoneNumber;
    @OneToOne(mappedBy = "person", fetch = FetchType.EAGER)
    private Vehicle vehicle;
}

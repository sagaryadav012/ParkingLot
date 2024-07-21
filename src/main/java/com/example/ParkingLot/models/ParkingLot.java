package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "parking_lots")
@Data
public class ParkingLot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    private ParkingLotStatus parkingLotStatus;
    @OneToMany(mappedBy = "parkingLot", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Floor> floors;
}

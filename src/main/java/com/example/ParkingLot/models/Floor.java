package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "floors")
@Data
public class Floor {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private int floorNumber;
    private FloorStatus floorStatus;
    @OneToMany(mappedBy = "floor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Slot> slots;
    @ManyToOne
    private ParkingLot parkingLot;
}

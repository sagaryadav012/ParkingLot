package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "slots")
@Data
public class Slot {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private int slotNumber;
    private VehicleType vehicleType;
    private SlotStatus slotStatus;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Vehicle vehicle;
    @ManyToOne
    private Floor floor;
}

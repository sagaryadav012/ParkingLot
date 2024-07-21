package com.example.ParkingLot.dtos;

import com.example.ParkingLot.models.VehicleType;
import lombok.Data;

@Data
public class ParkVehicleDTO {
    private long parkingLotNumber;
    private String vehicleType;
    private String registerNumber;
    private String color;
    private String ownerName;
    private String phoneNumber;
}

package com.example.ParkingLot.services;

import com.example.ParkingLot.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingLotService {
    ParkingLot createParkingLot(int floor_count, int slot_count);
    Ticket parkVehicle(long parkingLotNumber, String vehicleType, String registerNumber, String color, String ownerName, String phoneNumber);
    List<Slot> displaySlots(long parkingLotId, String vehicleType);
    Invoice unParkVehicle(long ticket_id);
}

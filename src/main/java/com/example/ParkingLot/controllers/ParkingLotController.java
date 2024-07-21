package com.example.ParkingLot.controllers;

import com.example.ParkingLot.dtos.ParkVehicleDTO;
import com.example.ParkingLot.models.*;
import com.example.ParkingLot.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ParkingLotController {
    private ParkingLotService parkingLotService;

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public ParkingLot createParkingLot(int floorCount, int slotCount){
        return this.parkingLotService.createParkingLot(floorCount, slotCount);
    }

    public Ticket parkVehicle(ParkVehicleDTO parkVehicleDTO){
        return this.parkingLotService.parkVehicle(parkVehicleDTO.getParkingLotNumber(), parkVehicleDTO.getVehicleType(), parkVehicleDTO.getRegisterNumber(),
                parkVehicleDTO.getColor(), parkVehicleDTO.getOwnerName(), parkVehicleDTO.getPhoneNumber());
    }

    public List<Slot> displaySlots(long parkingLotId, String vehicleType){
        return this.parkingLotService.displaySlots(parkingLotId, vehicleType);
    }

    public Invoice unParkVehicle(long ticket_id){
        return this.parkingLotService.unParkVehicle(ticket_id);
    }
}

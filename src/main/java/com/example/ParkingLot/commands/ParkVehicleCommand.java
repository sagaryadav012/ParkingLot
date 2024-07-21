package com.example.ParkingLot.commands;

import com.example.ParkingLot.controllers.ParkingLotController;
import com.example.ParkingLot.dtos.ParkVehicleDTO;
import com.example.ParkingLot.exceptions.InvalidCommandException;
import com.example.ParkingLot.models.Ticket;
import com.example.ParkingLot.models.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkVehicleCommand implements Command{
    private ParkingLotController controller;
    private static final String PARK_VEHICLE_KEY = "PARK_VEHICLE";

    @Autowired
    public ParkVehicleCommand(ParkingLotController controller) {
        this.controller = controller;
        CommandRegistry.getInstance().register(PARK_VEHICLE_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        String[] inputArr = input.split(" ");

        if(inputArr.length != 7) throw new InvalidCommandException("Invalid Command");

        long parkingLotNumber = Integer.parseInt(inputArr[1]);
        String vehicleType = inputArr[2];
        String registerNumber = inputArr[3];
        String color = inputArr[4];
        String ownerName = inputArr[5];
        String phoneNumber = inputArr[6];

        ParkVehicleDTO parkVehicleDTO = new ParkVehicleDTO();
        parkVehicleDTO.setParkingLotNumber(parkingLotNumber);
        parkVehicleDTO.setVehicleType(vehicleType);
        parkVehicleDTO.setRegisterNumber(registerNumber);
        parkVehicleDTO.setColor(color);
        parkVehicleDTO.setOwnerName(ownerName);
        parkVehicleDTO.setPhoneNumber(phoneNumber);

        Ticket ticket = this.controller.parkVehicle(parkVehicleDTO);

        System.out.println("Here are ticket details : "+
                           "\n Ticket ID : "+ ticket.getId() +
                           "\n Entry Time : "+ticket.getEntry_time() +
                           "\n Floor Number : "+ticket.getSlot().getFloor().getFloorNumber()+
                           "\n Slot Number : "+ticket.getSlot().getSlotNumber()+
                           "\n Vehicle Details : " + ticket.getSlot().getVehicleType()+", " +
                                                     ticket.getSlot().getVehicle().getRegisterNumber()+", "+
                                                     ticket.getSlot().getVehicle().getColor());
    }
}
/*
Command : PARK_VEHICLE 1 CAR TS28L4932 BLACK SAGAR 1234567890
          PARK_VEHICLE ParkingLotNumber vehicleType regNumber color ownerName phoneNumber
 */
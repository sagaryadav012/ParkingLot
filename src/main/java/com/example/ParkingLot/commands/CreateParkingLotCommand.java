package com.example.ParkingLot.commands;

import com.example.ParkingLot.controllers.ParkingLotController;
import com.example.ParkingLot.exceptions.InvalidCommandException;
import com.example.ParkingLot.models.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateParkingLotCommand implements Command{
    private static final String CREATE_PARKING_LOT_KEY = "CREATE_PARKING_LOT";
    private ParkingLotController controller;

    @Autowired
    private CreateParkingLotCommand(ParkingLotController controller){
        this.controller = controller;
        CommandRegistry.getInstance().register(CREATE_PARKING_LOT_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        String[] inputArr = input.split(" ");

        // Command must have 3 variable, Type of command, No.of Floors, No.of Slots.
        if(inputArr.length != 3) throw new InvalidCommandException("Invalid Command");

        int floor_count = Integer.parseInt(inputArr[1]);
        int slot_count = Integer.parseInt(inputArr[2]);

        ParkingLot parkingLot = this.controller.createParkingLot(floor_count, slot_count);

        if(parkingLot != null) System.out.println("Parking Lot Created");
    }
}
/*
Command : CREATE_PARKING_LOT 4 50
          CREATE_PARKING_LOT FLOOR_NUMBERS SLOT_NUMBERS
 */
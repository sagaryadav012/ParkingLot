package com.example.ParkingLot.commands;

import com.example.ParkingLot.controllers.ParkingLotController;
import com.example.ParkingLot.exceptions.InvalidCommandException;
import com.example.ParkingLot.models.Slot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DisplayAvailableSlotsCommand implements Command{
    private static final String DISPLAY_SLOTS_KEY = "DISPLAY_AVAILABLE_SLOTS";
    private ParkingLotController controller;

    public DisplayAvailableSlotsCommand(ParkingLotController controller){
        this.controller = controller;
        CommandRegistry.getInstance().register(DISPLAY_SLOTS_KEY, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        String[] inputArr = input.split(" ");

        if(inputArr.length != 3) throw new InvalidCommandException("Invalid Command...");

        long parkingLotId = Integer.parseInt(inputArr[1]);
        String vehicleType = inputArr[2];

        List<Slot> slots = this.controller.displaySlots(parkingLotId, vehicleType);

        System.out.println("Available slots for "+vehicleType+" : ");
        if(slots.isEmpty()) System.out.println("No slots available for "+vehicleType);

        for (Slot slot : slots) {
            System.out.println("Floor Number : "+slot.getFloor().getFloorNumber()+", Slot Number : "+slot.getSlotNumber());
        }
    }
}

/*
Command : DISPLAY_AVAILABLE_SLOTS 1 CAR
          DISPLAY_AVAILABLE_SLOTS ParkingLotNumber CAR

 */
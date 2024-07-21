package com.example.ParkingLot.strategies.slotAssignStrategy;

import com.example.ParkingLot.exceptions.NoSlotAvailableException;
import com.example.ParkingLot.models.*;
import org.springframework.stereotype.Component;

@Component
public class AssignLowestSlot implements AssignSlot{

    @Override
    public Slot assignSlot(ParkingLot parkingLot, VehicleType vehicleType) {
        for (Floor floor : parkingLot.getFloors()) {
            for (Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicleType) && slot.getSlotStatus().equals(SlotStatus.AVAILABLE)){
                    return slot;
                }
            }
        }
        throw new NoSlotAvailableException("No Slot Available...");
    }
}

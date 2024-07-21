package com.example.ParkingLot.strategies.slotAssignStrategy;

import com.example.ParkingLot.models.ParkingLot;
import com.example.ParkingLot.models.Slot;
import com.example.ParkingLot.models.VehicleType;
import org.springframework.stereotype.Component;

@Component
public interface AssignSlot {
    Slot assignSlot(ParkingLot parkingLot, VehicleType vehicleType);
}

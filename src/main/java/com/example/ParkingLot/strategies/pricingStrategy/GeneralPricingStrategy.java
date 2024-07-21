package com.example.ParkingLot.strategies.pricingStrategy;

import com.example.ParkingLot.Utils.DateTimeUtils;
import com.example.ParkingLot.models.VehicleType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GeneralPricingStrategy implements CalculatePrice{
    @Override
    public double calcPrice(Date entryTime, Date exitTime, VehicleType vehicleType) {
        int totalHoursSpent = DateTimeUtils.calculateHours(entryTime, exitTime);
        int price = getPrice(vehicleType);
        return totalHoursSpent * price;
    }
    public int getPrice(VehicleType vehicleType){
        return switch (vehicleType){
            case BIKE -> 20;
            case CAR -> 50;
            case TRUCK -> 80;
            default -> throw new RuntimeException("Invalid Vehicle Type");
        };
    }
}

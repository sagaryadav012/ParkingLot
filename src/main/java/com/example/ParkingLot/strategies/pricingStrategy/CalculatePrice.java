package com.example.ParkingLot.strategies.pricingStrategy;

import com.example.ParkingLot.models.VehicleType;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface CalculatePrice {
    double calcPrice(Date entryTime, Date exitTime, VehicleType vehicleType);
}

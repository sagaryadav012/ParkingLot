package com.example.ParkingLot.repositories;

import com.example.ParkingLot.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
}

package com.example.ParkingLot.repositories;

import com.example.ParkingLot.models.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepo extends JpaRepository<ParkingLot, Long> {
}

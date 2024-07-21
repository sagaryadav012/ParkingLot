package com.example.ParkingLot.repositories;

import com.example.ParkingLot.models.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlotRepo extends JpaRepository<Slot, Long> {
}

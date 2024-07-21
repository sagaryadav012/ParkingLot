package com.example.ParkingLot.repositories;

import com.example.ParkingLot.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}

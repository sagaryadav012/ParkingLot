package com.example.ParkingLot.repositories;

import com.example.ParkingLot.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {
}

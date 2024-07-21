package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Entity(name = "invoices")
@Data
public class Invoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    private Ticket ticket;
    @OneToOne(fetch = FetchType.EAGER)
    private Vehicle vehicle;
    private Date exit_time;
    private double chargedAmount;
}

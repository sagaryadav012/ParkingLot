package com.example.ParkingLot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "tickets")
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private Date entry_time;
    @ManyToOne(fetch = FetchType.EAGER)
    private Slot slot;
    private boolean isExpired;
}

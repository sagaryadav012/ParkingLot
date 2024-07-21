package com.example.ParkingLot.exceptions;

public class NoSlotAvailableException extends RuntimeException{
    public NoSlotAvailableException(String msg){
        super(msg);
    }
}

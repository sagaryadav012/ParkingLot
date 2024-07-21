package com.example.ParkingLot.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(String msg){
        super(msg);
    }
}

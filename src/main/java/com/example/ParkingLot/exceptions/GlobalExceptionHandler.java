package com.example.ParkingLot.exceptions;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCommandException.class)
    public void invalidCommandExceptionHandler(InvalidCommandException exception){
        System.out.println(exception.getMessage());
    }

    @ExceptionHandler(NoSlotAvailableException.class)
    public void noSlotAvailableExceptionHandler(NoSlotAvailableException exception){
        System.out.println(exception.getMessage());
    }

    @ExceptionHandler(InvalidParkingLotException.class)
    public void invalidParkingLotExceptionHandler(InvalidParkingLotException exception){
        System.out.println(exception.getMessage());
    }

    @ExceptionHandler(InvalidTicketException.class)
    public void invalidTicketExceptionHandler(InvalidTicketException exception){
        System.out.println(exception.getMessage());
    }
}

package com.example.ParkingLot.commands;

import com.example.ParkingLot.exceptions.InvalidCommandException;

public interface Command {
    void execute(String input) throws InvalidCommandException;
}

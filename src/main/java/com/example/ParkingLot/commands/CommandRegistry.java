package com.example.ParkingLot.commands;

import com.example.ParkingLot.exceptions.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private Map<String, Command> map;
    private static CommandRegistry instance;

    private CommandRegistry(){
        this.map = new HashMap<>();
    }

    public static CommandRegistry getInstance(){
        if(instance == null){
            instance = new CommandRegistry();
        }
        return instance;
    }

    public void register(String key, Command command){
        map.put(key, command);
    }
    public Command getCommand(String input){
        String command = input.split(" ")[0];
        for (Map.Entry<String, Command> entry : map.entrySet()) {
            String key = entry.getKey();
            if(command.equals(key)) return entry.getValue();
        }

        throw new InvalidCommandException("Command does not exist");
    }
}

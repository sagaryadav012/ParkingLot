package com.example.ParkingLot.commands;

import com.example.ParkingLot.controllers.ParkingLotController;
import com.example.ParkingLot.exceptions.InvalidCommandException;
import com.example.ParkingLot.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnParkVehicleCommand implements Command{
    private static final String UN_PARK_VEHICLE = "UN_PARK_VEHICLE";
    private ParkingLotController controller;

    @Autowired
    public UnParkVehicleCommand(ParkingLotController controller) {
        this.controller = controller;
        CommandRegistry.getInstance().register(UN_PARK_VEHICLE, this);
    }

    @Override
    public void execute(String input) throws InvalidCommandException {
        String[] inputArr = input.split(" ");
        if(inputArr.length != 2) throw new InvalidCommandException("Invalid Command");

        long ticket_id = Integer.parseInt(inputArr[1]);

        Invoice invoice = this.controller.unParkVehicle(ticket_id);

        System.out.println("Invoice Details : "+
                "\n Ticket Id : "+invoice.getTicket().getId()+
                "\n Vehicle Type : "+invoice.getVehicle().getVehicleType()+
                "\n Vehicle Registered Number : "+invoice.getVehicle().getRegisterNumber()+
                "\n Vehicle Color : "+invoice.getVehicle().getColor()+
                "\n Entry Time : "+invoice.getTicket().getEntry_time()+
                "\n Exit Time : "+invoice.getExit_time()+
                "\n Charged Amount : "+invoice.getChargedAmount());
    }
}

/*
Command : UN_PARK_VEHICLE 1
          UN_PARK_VEHICLE Ticket_id
 */
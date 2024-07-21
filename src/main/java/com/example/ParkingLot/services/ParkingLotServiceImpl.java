package com.example.ParkingLot.services;

import com.example.ParkingLot.exceptions.InvalidCommandException;
import com.example.ParkingLot.exceptions.InvalidParkingLotException;
import com.example.ParkingLot.exceptions.InvalidTicketException;
import com.example.ParkingLot.models.*;
import com.example.ParkingLot.repositories.InvoiceRepo;
import com.example.ParkingLot.repositories.ParkingLotRepo;
import com.example.ParkingLot.repositories.SlotRepo;
import com.example.ParkingLot.repositories.TicketRepo;
import com.example.ParkingLot.strategies.pricingStrategy.CalculatePrice;
import com.example.ParkingLot.strategies.slotAssignStrategy.AssignSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

    private ParkingLotRepo parkingLotRepo;
    private AssignSlot assignSlot;
    private SlotRepo slotRepo;
    private TicketRepo ticketRepo;
    private CalculatePrice calculatePrice;
    private InvoiceRepo invoiceRepo;

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepo parkingLotRepo, AssignSlot assignSlot, SlotRepo slotRepo, TicketRepo ticketRepo, CalculatePrice calculatePrice, InvoiceRepo invoiceRepo) {
        this.parkingLotRepo = parkingLotRepo;
        this.assignSlot = assignSlot;
        this.slotRepo = slotRepo;
        this.ticketRepo = ticketRepo;
        this.calculatePrice = calculatePrice;
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public ParkingLot createParkingLot(int floor_count, int slot_count) {
        ParkingLot parkingLot = new ParkingLot();

        List<Floor> floors = new ArrayList<>();
        for(int i = 1; i <= floor_count; i++){
            Floor floor = new Floor();
            floor.setParkingLot(parkingLot);
            floor.setFloorNumber(i);
            floor.setFloorStatus(FloorStatus.AVAILABLE);

            List<Slot> slots = new ArrayList<>();
            for(int j = 1; j <= slot_count; j++){
                Slot slot = new Slot();
                slot.setSlotNumber(j);
                slot.setSlotStatus(SlotStatus.AVAILABLE);
                slot.setVehicleType(getRandomVehicleType());
                slot.setFloor(floor);
                slots.add(slot);
            }
            floor.setSlots(slots);
            floors.add(floor);
        }

        parkingLot.setName("PARKING_LOT_"+new Random().nextInt(9));
        parkingLot.setParkingLotStatus(ParkingLotStatus.AVAILABLE);
        parkingLot.setFloors(floors);

        return parkingLotRepo.save(parkingLot);
    }

    @Override
    public Ticket parkVehicle(long parkingLotNumber, String vehicleType, String registerNumber, String color, String ownerName, String phoneNumber) {
        /*
            step 1 : Get parking lot using parkingLotNumber.
            step 2 : Assign slot.
            step 3 : Update slot, store vehicle, store person.
            step 4 : Generate ticket, set ticket isExpired true and return it.
         */
        VehicleType vehicle_type = getVehicleType(vehicleType);

        ParkingLot parkingLot = this.parkingLotRepo.findById(parkingLotNumber).orElseThrow(() -> new InvalidParkingLotException("Invalid ParkingLot"));
        Slot slot = this.assignSlot.assignSlot(parkingLot, vehicle_type);

        Person person = new Person();
        person.setName(ownerName);
        person.setPhoneNumber(phoneNumber);

        Vehicle vehicle = new Vehicle();
        vehicle.setRegisterNumber(registerNumber);
        vehicle.setVehicleType(vehicle_type);
        vehicle.setColor(color);
        vehicle.setPerson(person);

        slot.setSlotStatus(SlotStatus.OCCUPIED);
        slot.setVehicle(vehicle);

        Slot updatedSlot = this.slotRepo.save(slot);

        Ticket ticket = new Ticket();
        ticket.setSlot(updatedSlot);
        ticket.setEntry_time(new Date());
        ticket.setExpired(false); // It means ticket not expired

        return this.ticketRepo.save(ticket);
    }

    @Override
    public List<Slot> displaySlots(long parkingLotId, String vehicleType) {
        VehicleType vehicle_type = getVehicleType(vehicleType);

        ParkingLot parkingLot = parkingLotRepo.findById(parkingLotId).orElseThrow(() -> new InvalidParkingLotException("Invalid Parking Lot."));

        List<Slot> slots = new ArrayList<>();
        for (Floor floor : parkingLot.getFloors()) {
            for (Slot slot : floor.getSlots()) {
                if(slot.getVehicleType().equals(vehicle_type) && slot.getSlotStatus().equals(SlotStatus.AVAILABLE)){
                    slots.add(slot);
                }
            }
        }

        return slots;
    }

    @Override
    public Invoice unParkVehicle(long ticket_id) {
        /*
            step1 : Get ticket using ticket_id.
            step2 : Calculate amount.
            step3 : Set slot is available and remove vehicle associated with it.
            step4 : Generate invoice and return it.
         */
        Ticket ticket = this.ticketRepo.findById(ticket_id).orElseThrow(() -> new InvalidTicketException("Invalid Ticket"));
        if(ticket.isExpired()) throw new InvalidTicketException("Invalid ticket, it expired.");

        Slot slot = ticket.getSlot();

        double chargedAmount = this.calculatePrice.calcPrice(ticket.getEntry_time(), new Date(), slot.getVehicleType());
        Invoice invoice = new Invoice();
        invoice.setTicket(ticket);
        invoice.setExit_time(new Date());
        invoice.setChargedAmount(chargedAmount);
        invoice.setVehicle(ticket.getSlot().getVehicle());

        slot.setSlotStatus(SlotStatus.AVAILABLE);
        slot.setVehicle(null);
        this.slotRepo.save(slot);

        ticket.setExpired(true);
        this.ticketRepo.save(ticket);

        return invoiceRepo.save(invoice);
    }

    public VehicleType getRandomVehicleType(){
        List<VehicleType> vehicleTypes = List.of(VehicleType.CAR, VehicleType.TRUCK, VehicleType.BIKE);
        Random random = new Random();
        int randomIndex = random.nextInt(vehicleTypes.size());

        return vehicleTypes.get(randomIndex);
    }
    public VehicleType getVehicleType(String vehicleType){
        return switch (vehicleType) {
            case "CAR" -> VehicleType.CAR;
            case "BIKE" -> VehicleType.BIKE;
            case "TRUCK" -> VehicleType.TRUCK;
            default -> throw new InvalidCommandException("Invalid Vehicle Type");
        };
    }
}

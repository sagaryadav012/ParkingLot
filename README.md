# ParkingLot

Parking is an application which helps to assign slots to park vehicles for certain amount of time.

-> Here I used command line design pattern to get different objects for different commands.
-> Implemented single ton design pattern to store multiple commands in command registry which gives command
   object depends on input command string.
-> Implemented strategy design pattern to assign slots and calculate parking fee.   

Here is the flow :
-> Takes command from input string(string at 0 index is a command).
-> Check object in command registry and get object.
-> Once we get command object, then take inputs from input string and send to controller, There is
   Only one controller(ParkingLotController).
-> ParkingLotController pass inputs to service(ParkingLotService) Service class does business logic
   And will send respond back.
-> Here I used multiple repositories to do operations on different entities.

Let's see all commands one by one.

1. CREATE_PARKING_LOT :
-> This command used to create entire parking lot(Assume a BLOCK), Each parking lot has unique id.
   In which it has floors, Each floor has slots. Each slot has slot number, slot status, which
   vehicle type can be parked, parked vehicle details.
-> This command has 3 strings command, no.of floors, no.of slots.
   Command : CREATE_PARKING_LOT NO_OF_FLOORS NO_OF_SLOTS.
   Ex : CREATE_PARKING_LOT 3 10
-> It take inputs, will send to controller, controller will send service, service creates all floors
   and slots. And returns parkingLotId.
-> Each slot has vehicleType, So I take vehicle types are randomly with help of getRandomVehicleType()
   method in ParkingLotServiceImpl class.

2. DISPLAY_AVAILABLE_SLOTS :
-> This command displays all available slots for a given vehicle type.
-> Command : DISPLAY_AVAILABLE_SLOTS PARKING_LOT_ID VEHICLE_TYPE
   Ex : DISPLAY_AVAILABLE_SLOTS 1 CAR
-> Get parkingLot with help of given parkingLotId, And check each floor and each slot whether the slot
   vehicle type equal to given vehicle type and slot status equal to available, if yes add slot to
   list and return it.

3. PARK_VEHICLE :
-> This command used to assign a slot for a vehicle, and generate a ticket.
-> Command : PARK_VEHICLE PARKING_LOT_ID VEHICLE_TYPE VEHICLE_REGISTERED_NUMBER COLOR PERSON_NAME PHONE_NUMBER
   Ex : PARK_VEHICLE 1 CAR TS28L4992 BLACK SAGAR_YADAV 1234567890
-> It takes inputs of parkingLotId, vehicleType, vehicleRegisteredNumber, color, ownerName of vehicle,
   phoneNumber.
-> There are multiple ways to assign a slot for vehicle, Here I chose assign a lowestNumber slot in
   lowestNumber floor. There are multiple ways So I have implemented strategy design pattern to assign
   slots.
-> Once a slot assigned, It generates a ticket and returns it.

4. UN_PARK_VEHICLE :
-> This command used to un park vehicle get invoice for parking fee.
-> Command : UN_PARK_VEHICLE TICKET_ID
   Ex : UN_PARK_VEHICLE 1
-> There are multiple to charge, so I used again strategy design pattern to calculate parking fee.
-> Get ticket using ticket_id. Calculated no.of hours parked using DateTime utils. And get parking
   fee depends on vehicle type.
-> Once we get total amount, generate Invoice and update slot status and set vehicle to null in slot.
-> Return invoice.

Problems Faced :
-> I set fetch type to EAGER in all entities, else we get LazyInitializationException since associated
   entities doesn't load when parent entity loaded.
-> I set cascade type to ALL, because when assigned a slot, we had to store vehicle and person details.
   we can do it with help respective repository. since I set cascade type to ALL so when parent entity(slot)
   updated then all it's associated entities(Vehicle, Person) data also will be updated in tables.
-> I got duplicate entry error when I park vehicle, un park vehicle and parked again vehicle on same slot.
   because I have given relation OneToOne to Slot in Ticket. because of this when we park vehicle,
   It generates ticket and store in db. when un park vehicle, slot become empty, and park vehicle on
   same slot, ticket store same slot id ticket table, that means it store duplicate value.
-> The error goes away when I have given relation as ManyToOne to Slot in Ticket, since Ticket can have
   multiple records of same slot.

Exception Handling :
-> I used global exception handler to handle all exceptions.
-> When an exception, it comes to GlobalExceptionHandler class, there we have multiple exception
   handler for different exceptions, it searches for correspondent exception and execute that method.

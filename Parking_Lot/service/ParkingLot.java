@Getter
public class ParkingLot {
  
  private static final ParkingLot instance = new ParkingLot();
  
  private final Map<String,ParkingFloor> floors = new HashMap<>();
  private final Map<String, Ticket> activeTickets = new HashMap<>();

  @Setter
  private PricingStrategy pricingStrategy; // an Interface with "double calculateFee(vehicleType,entryTime,exitTime)" method

  private ParkingLot() {
    this.pricingStrategy = PricingStrategyFactory.get(PricingStrategyType.TIME_BASED); // returns an implementation of PricingStrategy with method " PricingStrategy get(PricingStrategyType type) "
  }

  public static ParkingLot getInstance() {
    return instance;
  }

  public void addFloor(ParkingFloor floor) { // is a class with an id and map of parkingspots
    floors.put(floor.getId(),floor);
  }

  public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entryTime) {
    for(ParkingFloor floor : floors.values()) {

      ParkingSpot spot = floor.findAvailableSpot(vehicle.getType());

      // if spots are available, you will return an instance and Ticket will be provied
      if(spot !=null){
        String ticketId = UUID.randomUUID().toString();
        Ticket ticket = Ticket.builder()
                        .ticketId(ticketId)
                        .entryTime(entryTime)
                        .vehicle(vehicle)
                        .floorId(floor.getId())
                        .spotId(spot.getId())
                        .build();

        activeTickets.put(ticketId,ticket);
        System.out.println("Vehicle parked. Ticket:" + ticketId);
        
        return ticket;
      }        
    }

    System.out.println("No spot available for vehicle type:"+vehicle.getType());
    return null;
  }

  public void unParkVehicle(String ticketId,LocalDateTime exitTime, PaymentMode paymentMode) { // this is where payment is done 
  Ticket ticket = activeTickets.get(ticketId);
    if(ticket == null ) {
      System.out.println("Invalid ticket ID.");
    }

    double fee = pricingStrategy.calculateFee(ticket.getVehicle().getType(),
                                              ticket.getEntryTime(),
                                              exitTime );
    
    PaymentStrategy strategy = PaymentStrategyFactory.get(paymentMode); // PaymentStrategy is an interface with "boolean processPayment(ticket, amount);
                                                                        
    PaymentProcessor processor = new PaymentProcessor(strategy); // PaymentProcessor is class which takes paymentStrategy
                                                                // and has method "boolean pay(ticket,amount)
    boolean paid = processor.pay(ticket,fee);

    if(!paid) {
      System.out.println("Payment Failed. Please Try again")
      return ;    
    }


    // ParkingFloor is class -> has a -> map.of(parkingspotid, ParkingSpot) = spots
    // now floor is map.of(floorid,floor);
    // now ticket object has floorId, spotId
    ParkingSpot spot = floors.get(ticket.getFloorId()) // returns a ParkingFloor object
                      .getSpots()     // returns a map of ParkingSpots of PrakingFloorObject
                      .get(ticket.getSpotId());  // returns a ParkingSpot

    // ParkingSpot is class with AtomicBoolean with methods as tryOccupy() and vacate() method
      spot.vacate();
      activeTickets.remove(ticketId);

    System.out.println("Vehicle exited. Fee charged: "+fee);  
  }    

  public void printStatus() {
    floors.forEach( (floorId,floor) -> {
        System.out.println("Floor: "+floorId);
        floor.getSpots().values().forEach( spot -> {
          System.out.println(" Spot " + spot.getId() + " [" + spot.getAllowedType() + "] - " + (spot.isOccupied() ? "Occupied" : "Free"));
          });
      });
    }   
}

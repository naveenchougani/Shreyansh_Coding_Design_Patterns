import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

pubic class Main {
  public static void main(String args[]) {
    ParkingLot lot = ParkingLot.getInstance();
    EntryGate entryGate = new EntryGate("EN1");
    ExitGate exitGate = new ExitGate("EX1");

    lot.setPricingStrategy(PricingStrategyFactory.get(PricingStrategyType.valueOf("EVENT_BASED")));

    ParkingFloor floor1= new ParkingFloor("Floor1");

    floor1.addSpot(new ParkingSpot("F1S1", VehicleType.BIKE));
    floor1.addSpot(new ParkingSpot("F1S2", VehicleType.CAR));
    floor1.addSpot(new ParkingSpot("F1S3", VehicleType.TRUCK));
    floor1.addSpot(new ParkingSpot("F1S4", VehicleType.CAR));

    lot.addFloor(floor1);

    Vehicle bike1 = VehicleFactory.create("KA01AB1234", VehicleType.BIKE);
    Vehicle bike2 = VehicleFactory.create("KA01AB5678", VehicleType.BIKE);

    LocalDateTime entryTime = DateTimeParser.parse("21 May 7:30 AM 2025");
    System.out.println(entryTime.truncatedTo(ChronoUnit.HOURS));

    ExecutorService executor = Executors.newFixedThreadPool(2);

    Future<Ticket> futureTicket1 = executor.submit(() -> entryGate.parkVehicle(bike1, entryTime));
    Future<Ticket> futureTicket1 = executor.submit(() -> entryGate.parkVehicle(bike2, entryTime));

      Ticket bikeTicket1 = futureTicket1.get();
      Ticket bikeTicket2 = futureTicket2.get();

    
      Vehicle car = VehicleFactory.create("KA01AB1234", VehicleType.CAR);

      LocalDateTime entryTime = DateTimeParser.parse("21 May 7:30 AM 2025");
      Ticket carTicket = entryGate.parkVehicle(car, entryTime);

      System.out.println("--------------------------");

      lot.printStatus();

      System.out.println("--------------------------");

      LocalDateTime exitTime = DateTimeParser.parse("21 May 1:15 PM 2025");

      if(carTicket!=null {
      exitGate.unparkVehicle(carTicket.getTicketId(), exitTime, PaymentMode.UPI);
      }
      if(bikeTicket1!=null {
      exitGate.unparkVehicle(bikeTicket1.getTicketId(), exitTime, PaymentMode.UPI);
      }
      if(bikeTicket2!=null {
      exitGate.unparkVehicle(bikeTicket2.getTicketId(), exitTime, PaymentMode.UPI);
      }

      System.out.println("--------------------------");

      lot.printStatus();
    
  }
}

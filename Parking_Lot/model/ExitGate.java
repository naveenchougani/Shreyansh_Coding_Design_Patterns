public class ExitGate extends Gate {

  public ExitGate(String id){
    super(id);
  }

  @Overrid
  public GateType getType(){
    return GateType.EXIT;
  }

  public void unparkVehicle(String ticketId,LocalDateTime exitTime,PaymentMode paymentMode){

    ParkingLot.getInstance().unparkVehicle(ticketId,exitTime,paymentMode);
    
  }
  
}

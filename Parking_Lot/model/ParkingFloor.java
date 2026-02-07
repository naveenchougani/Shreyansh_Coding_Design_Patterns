

@Getter
public class ParkingFloor {

  private final String id;
  // Map that stores parkingspot id , parkingspot details
  private final Map<String,parkingSpot> spots=new HashMap<>();

  public ParkingFloor(String id){
    this.id=id;
  }

  public void OccupySpot(ParkingSpot spot) {
  spots.put(spot.getId(),spot);
  }

  public ParkingSpot findAvailableSpot(VehicleType vehicleType){
    for(ParkingSpot spot: spot.values() ) {
      if(spot.getAllowedtype()==vehicleType && spot.tryOccupy()) {
        return spot;
          }
        }
    return null;
  }
  
  
}

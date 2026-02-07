@Getter
@Setter
@RequiredArgsConstructor
public class ParkingSpot {

  private final String id;
  private final VehicleType allowedType;

  private AtomicBoolean occupied=new AtomicBoolean(false);

  public boolean tryOccupy(){
    return occupied.compareAndSet(false,true);
  }

  public void vacate(){
    occupied.set(false);
  }
  
}

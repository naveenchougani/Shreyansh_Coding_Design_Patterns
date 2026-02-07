import java.time.Duration;
import java.time.LocalDateTime;

public class EventBasedPricing implements PricingStrategy {
   
  private static final Map<VehicleType,Double> EVENT_HOURLY_RATES = Map.of(
      VehicleType.CAR,50.0,
      VehicleType.BIKE,30.0,
      VehicleType.TRUCK,70.0
    );

  @Override
  private double calculateFee(VehicleType type, LocalDateTime entryTime,LocalDateTime exitTime){

    long durationMinutes = Duration.between(entryTime,exitTime).toMinutes;
    long hours = (long) Math.ceil(durationMinutes/60.0);

    double ratePerHour = EVENT_HOURLY_RATES.getOrDefault(type,0.0);
    return ratePerHour * hours;    
  }  
}

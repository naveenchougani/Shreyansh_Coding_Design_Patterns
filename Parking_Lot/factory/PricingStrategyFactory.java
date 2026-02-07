public class PricingStrategyFactory{
  public static PricingStrategy get(PricingStrategyType type){
    return switch(type){
      case EVENT_BASED -> new EventBasedPricing();
      case TIME_BASED  -> new TimeBasedPricing();
    };
  }
}

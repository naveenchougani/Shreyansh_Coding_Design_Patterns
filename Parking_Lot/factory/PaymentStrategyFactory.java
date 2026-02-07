public class PaymentStrategyFactory{

  public static PaymentStrategy get(PaymentMode mode){
    return switch(mode)->{
      case UPI -> new UpiPayment();
      case CASH -> new CashPayment();
      case CARD -> new CardPayment();
    };
  }
  
}

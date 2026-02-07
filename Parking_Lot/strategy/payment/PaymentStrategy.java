public interface PaymentStrategy{
  boolean processPayment(Ticket ticket,double amount);
}

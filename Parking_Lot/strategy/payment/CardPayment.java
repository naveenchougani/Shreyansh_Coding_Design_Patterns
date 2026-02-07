public class CardPayment extends PaymentStrategy {
  @Override
  public boolean processPayment(Ticket ticket,double amount) {
    System.out.println("paid Rs. "+amount+" for ticket "+ticket.getTicketId()+" via Card.");
    return true;
  }
}

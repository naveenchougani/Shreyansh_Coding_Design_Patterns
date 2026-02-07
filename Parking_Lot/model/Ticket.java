// Data means 
// Setter, Getter, toString(),equals(),hashCode()


@Data
@Builder
public class Ticket {

  private String ticketId;
  private LocalDateTime entryTime;
  private Vehicle vehicle;
  private String floorId;
  private String spotId;

  @Builder.Default
  private PaymentStatus paymentStatus=PaymentStatus.PENDING;  
}

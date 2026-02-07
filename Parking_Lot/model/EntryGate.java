public class EntryGate extends Gate {

  public EntryGate(String id) {
    super(id);
  }

  @Override
  public GateType getType() {
    return GateType.ENTRY;
  }
  
}

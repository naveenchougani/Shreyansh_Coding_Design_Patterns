public class ExitGate extends Gate {

  public ExitGate(String id){
    super(id);
  }

  @Overrid
  public GateType getType(){
    return GateType.EXIT;
  }
  
}

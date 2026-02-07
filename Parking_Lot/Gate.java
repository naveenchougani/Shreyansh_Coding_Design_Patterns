import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Gate {
    protected final String id;

    public abstract GateType getType();
}

// Abstract classes helps to avoid generic implementation of templates
// which do not make any sense.
// Thats why instead of a class, abstract class would be beneficial

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class Vehicle {

  private final String number;
  private final VehicleType type;
  
}

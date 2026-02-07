import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeParser {
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM h:mm a yyyy",Locale.ENGLISH);
   return LocalDateTime.parse(input,formatter)
}

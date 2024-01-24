package sorcer.flatfinder;

import java.time.LocalDate;
import java.util.Date;

public class Tenant extends User {
    public Tenant(String firstName, String lastName, LocalDate birthDate) {
        super(firstName, lastName, birthDate);
    }
}

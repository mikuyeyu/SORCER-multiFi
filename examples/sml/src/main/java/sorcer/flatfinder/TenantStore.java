package sorcer.flatfinder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenantStore {
    public static List<Tenant> tenantList;
    public TenantStore() {
        this.tenantList = new ArrayList<>();

        tenantList.add(new Tenant("John", "Doe", LocalDate.parse("1990-01-01")));
        tenantList.add(new Tenant("Alice", "Smith", LocalDate.parse("1985-05-15")));
        tenantList.add(new Tenant("Bob", "Johnson", LocalDate.parse("1992-09-20")));
        tenantList.add(new Tenant("Eva", "Williams", LocalDate.parse("1988-11-30")));
        tenantList.add(new Tenant("Charlie", "Brown", LocalDate.parse("1980-03-10")));
    }
    public static List<Tenant> getTenants() {
        return tenantList;
    }
}

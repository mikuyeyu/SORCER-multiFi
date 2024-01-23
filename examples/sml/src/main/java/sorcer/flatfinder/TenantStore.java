package sorcer.flatfinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenantStore {
    private List<Tenant> tenantList;
    public TenantStore() {
        this.tenantList = new ArrayList<>();

        tenantList.add(new Tenant("John", "Doe", new Date("1990-01-01")));
        tenantList.add(new Tenant("Alice", "Smith", new Date("1985-05-15")));
        tenantList.add(new Tenant("Bob", "Johnson", new Date("1992-09-20")));
        tenantList.add(new Tenant("Eva", "Williams", new Date("1988-11-30")));
        tenantList.add(new Tenant("Charlie", "Brown", new Date("1980-03-10")));
    }
    public List<Tenant> getTenants() {
        return tenantList;
    }
}

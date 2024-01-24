package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TenantFinderImpl implements TenantFinder{
    @Override
    public Context find(Context context) throws RemoteException, ContextException {

        context.putValue("tenant",TenantStore.tenantList.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Tenant not found")));

        return context;
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {

        String lastName = (String) context.getValue("lastName");
        Tenant tenant = TenantStore.tenantList.stream()
                .filter(user -> user.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Tenant not found"));

        context.putValue("name", tenant.getFirstName());
        context.putValue("lastName", tenant.getLastName());
        context.putValue("birthDate", tenant.getBirthDate());

        return context;
    }

    @Override
    public Context findFlatmate(Context context) throws RemoteException, ContextException {

        List<Tenant> tenants = TenantStore.getTenants();
        Tenant tenant = tenants.stream()
                .findFirst()
                .orElse(null);

        if (tenant == null) {
            throw new NoSuchObjectException("Tenant not found");
        }

        List<User> potentialFlatmates = new ArrayList<>();

        for (User flatmate : tenants) {
            if (!flatmate.equals(tenant)) {
                int ageDifference = calculateAgeDifference(flatmate.getBirthDate(), tenant.getBirthDate());

                if (Math.abs(ageDifference) <= 10 || Math.abs(ageDifference) >= 10) {
                    potentialFlatmates.add(flatmate);
                }
            }
        }

        context.putValue("flatmates",potentialFlatmates);
        return context;
    }
    private int calculateAgeDifference(LocalDate date1, LocalDate date2) {
        // Obliczanie różnicy wieku w latach
        long year1 = date1.getYear();
        long year2 = date2.getYear();

        return (int) Math.abs(year2 - year1);
    }
}


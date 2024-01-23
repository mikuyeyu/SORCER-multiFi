package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
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

        context.putValue("name", tenant.firstName);
        context.putValue("lastName", tenant.lastName);
        context.putValue("birthDate", tenant.birthDate);

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
    private int calculateAgeDifference(Date date1, Date date2) {
        // Obliczanie różnicy wieku w latach
        long milliseconds1 = date1.getTime();
        long milliseconds2 = date2.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffYears = diff / (24 * 60 * 60 * 1000 * 365L);

        return (int) diffYears;
    }
}


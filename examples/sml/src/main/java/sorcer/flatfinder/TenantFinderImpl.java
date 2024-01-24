package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("rawtypes")
public class TenantFinderImpl implements TenantFinder {
    public static final String TENANT_RESULT_ID = "result/tenant";
    public static final String TENANT_ARG_ID = "arg/tenant";
    public static final String LASTNAME_ARG_ID = "arg/lastName";
    public static final String FLATMATE_RESULT_ID = "resul/flatmates";
    public static final String LASTNAME_RESULT_ID = "resul/lastName";
    public static final String NAME_RESULT_ID = "resul/name";
    public static final String BIRTHDATE_RESULT_ID = "resul/birthDate";
    @Override
    public Context findBest(Context context) throws RemoteException, ContextException {
        Tenant tenant = TenantStore.tenantList.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Tenant not found"));

        context.putValue(TENANT_RESULT_ID, tenant);

        return context;
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {
        String lastName = (String) context.getValue(LASTNAME_ARG_ID);
        Tenant tenant = TenantStore.tenantList.stream()
                .filter(user -> user.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Tenant not found"));

        context.putValue(NAME_RESULT_ID, tenant.getFirstName());
        context.putValue(LASTNAME_RESULT_ID, tenant.getLastName());
        context.putValue(BIRTHDATE_RESULT_ID, tenant.getBirthDate());

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

        context.putValue(FLATMATE_RESULT_ID, potentialFlatmates);
        return context;
    }

    private int calculateAgeDifference(LocalDate date1, LocalDate date2) {
        // Obliczanie różnicy wieku w latach
        long year1 = date1.getYear();
        long year2 = date2.getYear();

        return (int) Math.abs(year2 - year1);
    }
}


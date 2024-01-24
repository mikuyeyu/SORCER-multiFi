package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class OwnerFinderImpl implements OwnerFinder {
    public static final String OWNER_ARG_FLATNAME = "arg/flatName";
    public static final String OWNER_ARG_LASTNAME = "arg/lastName";
    public static final String OWNER_RESULT = "result/owner";

    @Override
    public Context findByFlatName(Context context) throws RemoteException, ContextException {
        String flatName = (String) context.getValue(OWNER_ARG_FLATNAME);
        for (Owner owner : OwnerStore.owners) {
            Flat[] ownerFlats = owner.getFlats();
            Flat flat = Arrays.stream(ownerFlats)
                    .filter(f -> f.street.equals(flatName))
                    .findFirst()
                    .orElse(null);

            if (flat != null) {
                context.putValue(OWNER_RESULT, owner);
                return context;
            }
        }

        throw new NoSuchObjectException("Owner not found");
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {
        String lastName = (String) context.getValue(OWNER_ARG_LASTNAME);
        Owner owner = OwnerStore.owners.stream()
                .filter(o -> o.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Owner not found"));

        context.putValue(OWNER_RESULT, owner);
        return context;
    }
}

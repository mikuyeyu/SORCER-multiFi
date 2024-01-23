package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class OwnerFinderImpl implements OwnerFinder {
    @Override
    public Context findByFlatName(Context context) throws RemoteException, ContextException {
        String flatName = (String) context.getValue("flatName");

        for (Owner owner : OwnerStore.owners) {
            Flat[] ownerFlats = owner.getFlats();
            Flat flat = Arrays.stream(ownerFlats)
                    .filter(f -> f.street.equals(flatName))
                    .findFirst()
                    .orElse(null);

            if (flat != null) {
                context.putValue("owner", owner);
                return context;
            }
        }

        throw new NoSuchObjectException("Owner not found");
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {
        String lastName = (String) context.getValue("lastName");
        Owner owner = OwnerStore.owners.stream()
                .filter(o -> o.lastName.equals(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Owner not found"));

        context.putValue("firstName", owner.firstName);
        context.putValue("lastName", owner.lastName);
        context.putValue("birthDate", owner.birthDate);
        return context;
    }
}

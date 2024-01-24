package sorcer.flatfinder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.Identifiable;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class OwnerFinderImpl implements OwnerFinder {
    @Override
    public Context findByFlatName(Context context) throws RemoteException, ContextException {
        String flatName = (String) context.getValue("arg/flatName");
        for (Owner owner : OwnerStore.owners) {
            Flat[] ownerFlats = owner.getFlats();
            Flat flat = Arrays.stream(ownerFlats)
                    .filter(f -> f.street.equals(flatName))
                    .findFirst()
                    .orElse(null);

            if (flat != null) {
                context.putValue("result/owner", owner);
                return context;
            }
        }

        throw new NoSuchObjectException("Owner not found");
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {
        String lastName = (String) context.getValue("arg/lastName");
        Owner owner = OwnerStore.owners.stream()
                .filter(o -> o.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElseThrow(() -> new NoSuchObjectException("Owner not found"));

        context.putValue("result/details", owner.toString());
        return context;
    }
}

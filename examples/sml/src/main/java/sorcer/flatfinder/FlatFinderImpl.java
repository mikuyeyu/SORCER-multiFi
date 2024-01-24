package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.Exerter;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

@SuppressWarnings("rawtypes")
public class FlatFinderImpl implements FlatFinder {
    public static final String FLAT_RESULT_ID = "result/flat";
    public static final String FLAT_ARG_ID = "arg/flat";
    public static final String TENANT_ARG_ID = "arg/tenant";
    public static final String MAX_DISTANCE_ARG_ID = "arg/maxDistance";
    public static final String STREET_ARG_ID = "arg/street";
    public static final String FLAT_TENANT_RESULT = "result/flatTenant";

    @Override
    public Context findBest(Context context) throws RemoteException, ContextException {
        // dummy flat finder algorithm service
        Integer maxDistance = (Integer) context.getValue(MAX_DISTANCE_ARG_ID);
        if (maxDistance < 50){
            putMockedWarsawWawelskaFlat(context);
        }
        else {
            putMockedWarsawPotulickaFlat(context);
        }

        return context;
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException, NoSuchObjectException {
        String street = (String) context.getValue(STREET_ARG_ID);
        Flat flat = FlatStore.flats.getOrDefault(street, null);
        if (flat == null){
            throw new NoSuchObjectException("Flat not found");
        }

        context.putValue(FLAT_RESULT_ID, flat);
        return context;
    }

    @Override
    public Context findNearest(Context context) throws ContextException {
        // dummy nearest flat finder service
        Flat flat = FlatStore.flats.get("Wawelska");
        context.putValue(FLAT_RESULT_ID, flat);
        return context;
    }

    @Override
    public Context addTenant(Context context) throws RemoteException, ContextException {
        Flat flat = (Flat) context.getValue(FLAT_ARG_ID);
        Tenant tenant = (Tenant) context.getValue(TENANT_ARG_ID);

        flat.tenant = tenant;

        context.putValue(FLAT_TENANT_RESULT, flat);
        return context;
    }

    private void putMockedWarsawWawelskaFlat(Context context) throws ContextException {
        Flat flat = FlatStore.flats.get("Wawelska");
        context.putValue(FLAT_RESULT_ID, flat);

    }

    private void putMockedWarsawPotulickaFlat(Context context) throws ContextException {
        Flat flat = FlatStore.flats.get("Potulicka");
        context.putValue(FLAT_RESULT_ID, flat);
    }
}

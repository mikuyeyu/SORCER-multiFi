package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.Exerter;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

@SuppressWarnings("rawtypes")
public class FlatFinderImpl implements FlatFinder {
    private boolean isInitialized;
    private Exerter provider;

    public void init(Exerter provider) throws RuntimeException {
        if (isInitialized){
            throw new RuntimeException("Flat finder already initialized");
        }

        this.provider = provider;
        this.isInitialized = true;
    }

    @Override
    public Context findBest(Context context) throws RemoteException, ContextException {
        // dummy flat finder algorithm service
        Double maxDistance = (Double) context.getValue("maxDistance");
        if (maxDistance < 50){
            putMockedWarsawWawelskaFlat(context);
        }
        else {
            putMockedWarsawPotulickaFlat(context);
        }

        provider.fireEvent();
        return context;
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException, NoSuchObjectException {
        String street = (String) context.getValue("street");
        Flat flat = FlatStore.flats.getOrDefault(street, null);
        if (flat == null){
            throw new NoSuchObjectException("Flat not found");
        }

        context.putValue("city", flat.city);
        context.putValue("street", flat.street);
        context.putValue("buildingNumber", flat.buildingNumber);
        return context;
    }

    @Override
    public Context findNearest(Context context) throws ContextException {
        // dummy nearest flat finder service
        Flat flat = FlatStore.flats.get("Wawelska");
        context.putValue("city", flat.city);
        context.putValue("street", flat.street);
        context.putValue("buildingNumber", flat.buildingNumber);
        return context;
    }

    private void putMockedWarsawWawelskaFlat(Context context) throws ContextException {
        Flat flat = FlatStore.flats.get("Wawelska");
        context.putValue("city", flat.city);
        context.putValue("street", flat.street);
        context.putValue("buildingNumber", flat.buildingNumber);
    }

    private void putMockedWarsawPotulickaFlat(Context context) throws ContextException {
        Flat flat = FlatStore.flats.get("Potulicka");
        context.putValue("city", flat.city);
        context.putValue("street", flat.street);
        context.putValue("buildingNumber", flat.buildingNumber);
    }
}

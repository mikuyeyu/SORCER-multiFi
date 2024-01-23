package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.service.Exerter;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;

@SuppressWarnings("rawtypes")
public interface FlatFinder {
    void init(Exerter exerter) throws RuntimeException;

    Context findBest(Context context) throws RemoteException, ContextException;

    Context getDetails(Context context) throws RemoteException, ContextException, NoSuchObjectException;

    Context findNearest(Context context) throws RemoteException, ContextException;
}

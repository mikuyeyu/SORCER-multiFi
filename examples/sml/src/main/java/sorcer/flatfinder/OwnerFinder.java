package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface OwnerFinder {
    Context findByFlatName(Context context) throws RemoteException, ContextException;

    Context getDetails(Context context) throws RemoteException, ContextException;
}

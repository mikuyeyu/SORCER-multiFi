package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public interface TenantFinder {
    Context findFlat(Context context) throws RemoteException, ContextException;

    Context getDetails(Context context) throws RemoteException, ContextException;

    Context findFlatmate(Context context) throws RemoteException, ContextException;
}

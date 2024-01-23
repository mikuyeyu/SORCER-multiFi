package sorcer.flatfinder;

import sorcer.service.Context;
import sorcer.service.ContextException;

import java.rmi.RemoteException;

public class TenantFinderImpl implements TenantFinder{
    @Override
    public Context find(Context context) throws RemoteException, ContextException {
        return null;
    }

    @Override
    public Context getDetails(Context context) throws RemoteException, ContextException {
        return null;
    }

    @Override
    public Context findFlatmate(Context context) throws RemoteException, ContextException {
        return null;
    }
}

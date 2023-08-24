package rc;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRC implements RC {

	IRolerCoaster rc;
	@Override
	public boolean init(String host,int port) {
		try {
			Registry registry=LocateRegistry.getRegistry(host,port);
			String name="/rc";
			rc=(IRolerCoaster) registry.lookup(name);
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public Photo startRide(String name, String imeVozenog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endRide(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollerRide(String name) {
		try {
			rc.rollerRide(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rollerEnd(String name) {
		try {
			rc.endRide(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

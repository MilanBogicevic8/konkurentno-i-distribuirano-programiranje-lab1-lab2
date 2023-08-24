package rc;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRolerCoaster extends Remote {

	public Photo startRide(String name,String imeVozenog) throws RemoteException;
	public void endRide(String name) throws RemoteException;
	public void rollerRide(String name) throws RemoteException;
	public void rollerEnd(String name) throws RemoteException;
}

package rc;

import java.rmi.RemoteException;

public interface RC {
	
	public boolean init(String host,int port);
	public void close();
	public Photo startRide(String name,String imeVozenog);
	public void endRide(String name);
	public void rollerRide(String name);
	public void rollerEnd(String name);
}

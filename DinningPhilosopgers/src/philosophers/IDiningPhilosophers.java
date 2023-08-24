package philosophers;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDiningPhilosophers extends Remote {

	public void sit(String name,int id) throws RemoteException;
	public void go(String name, int id) throws RemoteException;
	public int getID(String name) throws Exception;
}

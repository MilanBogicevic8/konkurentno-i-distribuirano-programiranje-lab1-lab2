package philosophers;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiDP implements DP {

	IDiningPhilosophers dp;
	@Override
	public boolean init(String host, int port) {
		try {
			Registry registry=LocateRegistry.getRegistry(host,port);
			String name="/dp";
			dp=(IDiningPhilosophers) registry.lookup(name);
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

	}

	@Override
	public void sit(String name, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void go(String name, int id) {
		if(id==-1) return;
		// TODO Auto-generated method stub
		try {
			dp.go(name, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int getID(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

}

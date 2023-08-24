package lab2;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIAB implements AB {

	public IAtomicBroadcast ab;
	private int id=-1;
	
	@Override
	public boolean init(String host, int port) {
		try {
			Registry registry=LocateRegistry.getRegistry(host,port);
			String name="/ab";
			ab=(IAtomicBroadcast) registry.lookup(name);
			
			
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
	public boolean close() {
		//nothing to do
		return true;
	}

	@Override
	public void putGoods(String name, Goods goods) {
		// TODO Auto-generated method stub

	}

	@Override
	public Goods getGoods(String name) {
		try {
		if(id==-1) {
			id=ab.getId();
		}
		
		if(id==-1) {
			//to many consumers
			return null;
		}
		
		System.out.println(id);
			return ab.getGoods(name, id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

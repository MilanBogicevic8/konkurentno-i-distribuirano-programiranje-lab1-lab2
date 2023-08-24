package rw;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIRW implements RW{

	IReaderWriter rw;
	@Override
	public boolean init(String host, int port) {
		try {
			Registry registry=LocateRegistry.getRegistry(host,port);
			String name="/rw";
			rw=(IReaderWriter) registry.lookup(name);
			
			
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
		// nothing to cls
		return true;
	}

	@Override
	public void startWrite(String name, String line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endWrite(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Book startRead(String name) {
		try {
			System.out.println("citam");
			rw.startRead(name);
			return rw.read(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}

	@Override
	public void endRead(String name) {
		// TODO Auto-generated method stub
		System.out.println("zavrsio cit");
		try {
			rw.endRead(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

package lab2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAtomicBroadcast extends Remote {
	
	public void putGoods(String name,GoodsImpl good) throws RemoteException;
	public GoodsImpl getGoods(String name,int id) throws RemoteException;
	public int getId() throws RemoteException;
	
}

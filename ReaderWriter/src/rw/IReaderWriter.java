package rw;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IReaderWriter extends Remote {

	public void startRead(String name) throws RemoteException;
	public void endRead(String name) throws RemoteException;
	public void startWrite(String name) throws RemoteException;
	public void endWrite(String name) throws RemoteException;
	public Book read(String name) throws RemoteException;
	public void write(String name,String line) throws RemoteException;
}

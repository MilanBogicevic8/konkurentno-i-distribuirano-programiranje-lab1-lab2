package lab2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
	
	public static void main(String[] args) {
		int port=5000;
		//String host="localhost";
		int capacity=10;
		int consumers=10;
		
		IAtomicBroadcast ab=new AtomicBroadcast(capacity, consumers);
		
		try(ServerSocket ss=new ServerSocket(port)){
			
			
			
			IAtomicBroadcast stub=(IAtomicBroadcast) UnicastRemoteObject.exportObject(ab,0);
			
			System.out.println("CAO");
			//Registry registry=LocateRegistry.createRegistry(port);
			Registry registry= LocateRegistry.createRegistry(port+1); //pazi za jedan veci port
			System.out.println("cao");
			registry.bind("/ab", stub);
			
			System.out.println("Registry started");
			
			while(true) {
				
					Socket s=ss.accept();
					System.out.println("Accepted connection");
					new ServerThread(s,ab).start();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

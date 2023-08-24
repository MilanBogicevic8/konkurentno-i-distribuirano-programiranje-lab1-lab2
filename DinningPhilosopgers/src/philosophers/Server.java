package philosophers;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		int port=4000;
		IDiningPhilosophers dp=new DinningPhilosophers(5);
		
		try(ServerSocket ss=new ServerSocket(port)){
			
			IDiningPhilosophers stub=(IDiningPhilosophers) UnicastRemoteObject.exportObject(dp,0);
			Registry registry=LocateRegistry.createRegistry(port+1);
			String name="/dp";
			registry.rebind(name, stub);
			System.out.println("RMI connect...");
			while(true) {
				Socket client=ss.accept();
				new ServerThread(client,dp).start();
				System.out.println("NetConnect ...");
			}
			
			
		}catch(Exception e) {e.printStackTrace();}
	}
}

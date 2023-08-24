package rc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		int port=4000;
		IRolerCoaster rc=new RollerCoaster();
		
		try(ServerSocket ss=new ServerSocket(port)){
			
			IRolerCoaster stub=(IRolerCoaster) UnicastRemoteObject.exportObject(rc,0);
			Registry registry=LocateRegistry.createRegistry(port+1);
			
			String name="/rc";
			registry.rebind(name, stub);
			
			System.out.println("Uspesno povezivanje RMI....");
			while(true) {
				Socket client=ss.accept();
				System.out.println("NET accept...");
				new ServerThread(rc,client).start();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

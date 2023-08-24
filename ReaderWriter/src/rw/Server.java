package rw;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		IReaderWriter rw=new ReaderWriter();
		int port=4001;
		try(ServerSocket ss=new ServerSocket(port)){
			
			IReaderWriter stub=(IReaderWriter) UnicastRemoteObject.exportObject(rw,0);
			Registry registry=LocateRegistry.createRegistry(port+1);
			String name="/rw";
			registry.rebind(name, stub);
			System.out.println("RMI connect...");
			
			
			while(true) {
				Socket client=ss.accept();
				System.out.println("Net connect...");
				new ServerThread(rw,client).start();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

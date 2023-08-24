package lab2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	Socket s;
	IAtomicBroadcast ab;
	private ObjectInputStream ois;
	private ObjectOutputStream ous;
	private int id=-1;
	
	public ServerThread(Socket s, IAtomicBroadcast ab) throws IOException {
		this.s=s;
		this.ab=ab;
		
		ous=new ObjectOutputStream(s.getOutputStream());
		ois=new ObjectInputStream(s.getInputStream());
		
	}

	public void run() {
		boolean running=true;
		try {
		while(running==true) {
			
				String command=ois.readUTF();
				System.out.println("Received command: "+command);
				
				switch(command) {
					
				case "put":
					String name=ois.readUTF();
					GoodsImpl good=(GoodsImpl) ois.readObject();
					ab.putGoods(name, good);
					ous.writeObject("ACK");
					break;
				
				
				case "end":
					System.out.println("Ending socket connection");
					running=false;
					break;
				default:{
					System.out.println("Pogresili ste");
				}
			}
			
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

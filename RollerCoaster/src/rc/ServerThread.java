package rc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private IRolerCoaster rc;
	private Socket client;

	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public ServerThread(IRolerCoaster rc,Socket client) {
		this.rc=rc;
		this.client=client;
		
		try {
			oos=new ObjectOutputStream(client.getOutputStream());
			ois=new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
		boolean runn=true;
		
		try {
		while(runn) {
			
				String conn=ois.readUTF();
			
				switch(conn) {
				
				case "startr":
					String name=ois.readUTF();
					String imeVozenog=ois.readUTF();
					Photo photo=rc.startRide(name, imeVozenog);
					oos.writeObject(photo);
					break;
				case "endr":
					String name1=ois.readUTF();
					rc.endRide(name1);
					oos.writeObject("ACK");
					break;
				case "end":
					runn=false;
					break;
				default:
					System.out.println("Neka gr...");
				}
			
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				ois.close();
				oos.close();
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}

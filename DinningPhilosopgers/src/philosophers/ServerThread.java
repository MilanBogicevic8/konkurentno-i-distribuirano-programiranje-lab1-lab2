package philosophers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket client;
	private IDiningPhilosophers dp;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ServerThread(Socket client,IDiningPhilosophers dp) {
		this.client=client;
		this.dp=dp;
		try {
			oos=new ObjectOutputStream(client.getOutputStream());
			ois=new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		
		boolean running=true;
		String command = null;
		try {
		while(running) {
			
				command=ois.readUTF();
				
				switch(command.toLowerCase()) {
				
				case "sit":
					String name=ois.readUTF();
					Integer id=ois.readInt();
					dp.sit(name, id);
					oos.writeObject("ACK");
					break;
				case "id":
					String name1=ois.readUTF();
					//Integer id1=ois.readInt();
					int id1=dp.getID(name1);
					oos.writeInt(id1);
					
					oos.flush();
					System.out.println("id"+id1);
					break;
				case "end":
					running=false;
					break;
				default:
					System.out.println("Neka gresaka..");
				}
			
		}
		} catch (Exception e) {
			//System.out.println(command);
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch(Throwable t) {
		
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

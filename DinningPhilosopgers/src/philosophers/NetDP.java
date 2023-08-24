package philosophers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetDP implements DP {

	Socket client;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	int id=-1;
	
	@Override
	public void sit(String name, int id) {
		if(id==-1) return;
		try {
			oos.writeUTF("sit");
			oos.writeUTF(name);
			oos.writeInt(id);
			oos.flush();
			
			System.out.println("Seo sam i dobio poruku:"+ois.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void go(String name, int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID(String name) {
		if(id!=-1) {
			return id;
		}
		
		try {
			oos.writeUTF("id");
			oos.writeUTF(name);
			oos.flush();
			int id1=(int)ois.readInt();
			id=id1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}

	@Override
	public boolean init(String host, int port) {
		try {
			client=new Socket(host,port);
			oos=new ObjectOutputStream(client.getOutputStream()); ois=new ObjectInputStream(client.getInputStream());
			
			return true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void close() {
		try {
			oos.writeUTF("end");
			oos.flush();
			oos.close();
			ois.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}

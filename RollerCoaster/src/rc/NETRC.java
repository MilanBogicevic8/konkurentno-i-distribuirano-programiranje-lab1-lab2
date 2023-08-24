package rc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NETRC implements RC {

	Socket client;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	@Override
	public boolean init(String host, int port) {
		try {
			client=new Socket(host,port);
			oos=new ObjectOutputStream(client.getOutputStream());
			ois=new ObjectInputStream(client.getInputStream());
			System.out.println("uspesno");
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
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

	@Override
	public Photo startRide(String name, String imeVozenog) {
		try {
			oos.writeUTF("startr");
			oos.writeUTF(name);
			oos.writeUTF(imeVozenog);
			oos.flush();
			Photo photo=(Photo) ois.readObject();
			return photo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void endRide(String name) {
		try {
			oos.writeUTF("endr");
			oos.writeUTF(name);
			oos.flush();
			System.out.println("Dobio sam poruku"+ois.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rollerRide(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollerEnd(String name) {
		// TODO Auto-generated method stub

	}

}

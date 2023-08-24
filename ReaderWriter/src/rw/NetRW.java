package rw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetRW implements RW {

	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	@Override
	public boolean init(String host, int port) {
		try {
			s=new Socket(host, port);
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
			
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
	public boolean close() {
		try {
			oos.writeUTF("end");
			oos.flush();
			oos.close();
			ois.close();
			s.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public void startWrite(String name, String line) {
		try {
			oos.writeUTF("startw");
			oos.writeUTF(name);
			oos.writeUTF(line);
			oos.flush();
			System.out.println("Uspeo sam da upisem u fajl"+name+" "+ois.readObject());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void endWrite(String name) {
		try {
			oos.writeUTF("endw");
			oos.writeUTF(name);
			oos.flush();
			System.out.println("Uspeo sam da upisem i dobio: "+ois.readObject());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Book startRead(String name) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endRead(String name) {
		// TODO Auto-generated method stub
	}

}

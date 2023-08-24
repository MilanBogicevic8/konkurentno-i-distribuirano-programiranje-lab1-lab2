package lab2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetAB implements AB {

	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	@Override
	public boolean init(String host, int port) {
		try {
			s=new Socket(host,port);
			
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
			System.out.println("super");
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
	public void putGoods(String name, Goods goods) {
		try {
			oos.writeUTF("put");
			oos.writeUTF(name);
			oos.writeObject(goods);
			
			//dohvatanje poruke
			
			System.out.println("Poslao sam poruku i dobio:"+(String)ois.readObject());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	

	@Override
	public Goods getGoods(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}

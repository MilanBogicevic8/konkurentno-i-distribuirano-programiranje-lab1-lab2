package rw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {

	private Socket s;
	private IReaderWriter rw;

	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ServerThread(IReaderWriter rw,Socket s) {
		this.s=s;
		this.rw=rw;
		try {
			oos=new ObjectOutputStream(s.getOutputStream());
			ois=new ObjectInputStream(s.getInputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void run() {
		try {
			
			Boolean rad=true;
			while(rad==true) {
				
			String connect=ois.readUTF();
			System.out.println("dobijena kom"+connect);
			switch(connect){
			case "startw":
				System.out.println("Pisanje");
				String name=ois.readUTF();
				String line=ois.readUTF();
				rw.startWrite(name);
				rw.write(name, line);
				oos.writeObject("ACK");
				
				break;
			case "endw":
				System.out.println("endw");
				String name1=ois.readUTF();
				rw.endWrite(name1);
				oos.writeObject("ACK");
				break;
			case "end":
				rad=false;
				break;
			default:
				System.out.println("Pogresili ste instr...");
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

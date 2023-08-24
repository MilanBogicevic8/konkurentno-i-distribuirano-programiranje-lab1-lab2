package rw;

import java.rmi.RemoteException;
import java.util.HashMap;

public class ReaderWriter implements IReaderWriter {

	int cntR=0;
	int cntW=0;
	int ticket=0;
	int next=0;
	
	public class Resource{
		public int cntR=0;
		public int cntW=0;
		public Book book=new BookImpl();
	}
	
	private HashMap<String, Resource> mapa=new HashMap<>();
	
	@Override
	public synchronized void startRead(String name) throws RemoteException {
		int myTurn=ticket++;
		Resource res=this.mapa.get(name);
		if(res==null) {
			res=new Resource();
			mapa.put(name, res);
		}
		while(myTurn!=next || res.cntW>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.cntR++;
		next++;
		notifyAll();
	}

	@Override
	public synchronized void endRead(String name) throws RemoteException {
		Resource res=this.mapa.get(name);
		res.cntR--;
		if(res.cntR==0) {
			notifyAll();
		}
	}

	@Override
	public synchronized void startWrite(String name) throws RemoteException {
		int myTurn=ticket++;
		Resource res=this.mapa.get(name);
		if(res==null) {
			res=new Resource();
			mapa.put(name, res);
		}
		while(myTurn!=next || res.cntR>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.cntW++;
		//notifyAll();
	}

	@Override
	public synchronized void endWrite(String name) throws RemoteException {
		System.out.println("pocetak zavrsetka writ");
		Resource res=this.mapa.get(name);
		res.cntW--;
		next++;
		notifyAll();
		System.out.println("zavrseno pisanje write");

	}

	@Override
	public synchronized Book read(String name) {
		Resource res=this.mapa.get(name);
		if(res==null) {
			return null;
		}
		return res.book;
	}

	@Override
	public synchronized void write(String name,String line) {
		Resource res=this.mapa.get(name);
		if(res==null) {
			res=new Resource();
		}
		
		mapa.put(name, res);
		
		res.book.printLine(name);
		//notifyAll();
		System.out.println("upisanoa"+" "+line);
	}

}

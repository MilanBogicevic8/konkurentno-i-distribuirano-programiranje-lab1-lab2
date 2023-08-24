package rc;

import java.rmi.RemoteException;
import java.util.HashMap;

public class RollerCoaster implements IRolerCoaster {

	private int K=3;
	
	
	public class Resource{
		int ticket=0;
		int next=0;
		Photo photo=new PhotoImpl();
		int putnici=0;
		int izaslo=0;
		boolean dosao=false;
		boolean otisao=false;
	}
	
	private HashMap<String, Resource> mapa=new HashMap<>();
	
	
	@Override
	public synchronized Photo startRide(String name, String imeVozenog) throws RemoteException {
		Resource res=mapa.get(name);
		while(res==null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res=mapa.get(name);
		}
		
		
		
		while(res.dosao==false || res.putnici==K) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int myTurn=res.ticket++;
		
		while(myTurn!=res.next || res.putnici==K) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		res.putnici++;
		if(res.putnici==K) {res.izaslo=0;}
		res.photo.printLine(imeVozenog);
		res.next++;
		notifyAll();
		System.out.println(res.putnici+" "+K);
		while(res.putnici!=K) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res.photo;

	}

	@Override
	public synchronized void endRide(String name) throws RemoteException {
		Resource res=mapa.get(name);
		while(res.otisao==false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.izaslo++;
		System.out.println(res.izaslo);
		if(res.izaslo==K) {
			res.putnici=0;
			notifyAll();
		}

	}

	@Override
	public synchronized void rollerRide(String name) throws RemoteException {
		Resource res=mapa.get(name);
		if(res==null) {
			res=new Resource();
			mapa.put(name, res);
			notifyAll();
		}
		res.dosao=true;
		res.otisao=false;
		notifyAll();
		System.out.println(res.putnici+" "+K);
		while(res.putnici!=K) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void rollerEnd(String name) throws RemoteException {
		Resource res=mapa.get(name);
		res.dosao=false;
		res.otisao=true;
		res.putnici=0;
		
		notifyAll();
		

	}

}

package philosophers;

import java.rmi.RemoteException;
import java.util.HashMap;

public class DinningPhilosophers implements IDiningPhilosophers {

	public int numP;
	public class Resource{
		public boolean[] forks;
		int ticket=0;
		int next=0;
		int id=0;
		public Resource() {
			forks=new boolean[numP];
			for(int i=0;i<numP;i++) {
				forks[i]=false;
			}
		}
		
		public int getID() {
			if(id==numP) {
				return -1;
			}
			return id++;
		}
	}
	
	public DinningPhilosophers(int numP) {
		this.numP=numP;
	}
	
	public HashMap<String, Resource> mapa=new HashMap<>();
	@Override
	public synchronized void sit(String name,int id) throws RemoteException {
		Resource res=mapa.get(name);
		if(res==null) {
			res=new Resource();
		}
		
		int myTicket=res.ticket++;
		
		while(myTicket!=res.next && (res.forks[(id-1)%numP]==true || res.forks[(id+1)%numP]==true)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		res.forks[id]=true;
		res.next++;
		notifyAll();
	}

	@Override
	public synchronized void go(String name, int id) throws RemoteException {
		Resource res=mapa.get(name);
		if(res==null) {
			return;
		}
		
		res.forks[id]=false;
		notifyAll();

	}

	@Override
	public synchronized int getID(String name) throws Exception {
		Resource res=mapa.get(name);
		if(res==null) {
			res=new Resource();
			mapa.put(name, res);
		}
		return res.getID();
	}

}

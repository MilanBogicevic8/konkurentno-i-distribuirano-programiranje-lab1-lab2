package lab2;

import java.rmi.RemoteException;
import java.util.HashMap;

import javax.xml.crypto.Data;

public class AtomicBroadcast implements IAtomicBroadcast {

	private final int capacity;
	private final int consumers;
	
	public class GoodsResource{
		GoodsImpl[] data;
		int[] consumerIndex;
		int[] consumerCount;
		int producerIndex;
		int size;
		
		public GoodsResource() {
			data=new GoodsImpl[capacity];
			consumerIndex=new int[capacity];
			consumerCount=new int[consumers];
			for(int i=0;i<capacity;i++) {
				data[i]=null;
				consumerIndex[i]=0;
			}
			for(int i=0;i<consumers;i++) {
				consumerCount[i]=0;
			}
			producerIndex=0;
			size=0;
		}
	}
	
	private HashMap<String, GoodsResource> mapa=new HashMap<>();
	private int lastConsumerID=0;
	
	public AtomicBroadcast(int capacity,int consumers) {
		this.capacity=capacity;
		this.consumers=consumers;
	}
	
	@Override
	public synchronized void putGoods(String name, GoodsImpl good) throws RemoteException {
		GoodsResource resource=mapa.get(name);
		if(resource==null) {
			resource=new GoodsResource();
			mapa.put(name, resource);
		}
		
		while(resource.size==capacity) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		resource.data[resource.producerIndex]=good;
		resource.consumerIndex[resource.producerIndex]=consumers;
		resource.producerIndex=(resource.producerIndex+1)%capacity;
		++resource.size;
		
		notifyAll();

	}

	@Override
	public synchronized GoodsImpl getGoods(String name, int id) throws RemoteException {
		
		GoodsResource resource=mapa.get(name);
		
		if(resource==null) {
			resource=new GoodsResource();
			mapa.put(name, resource);
		}
		
		while(resource.size==0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		GoodsImpl good=resource.data[resource.consumerIndex[id]];
		resource.consumerCount[resource.consumerIndex[id]]--;
		resource.consumerIndex[id]=(resource.consumerIndex[id]+1)%capacity;
		
		resource.consumerCount[id]--;
		if(resource.consumerCount[id]==0) {
			resource.size--;
		}
		
		notifyAll();
		
		return good;
		
		
	}

	@Override
	public synchronized int getId() throws RemoteException {
		if(lastConsumerID==consumers) {
			return -1;
		}
		return lastConsumerID++;
	}

}

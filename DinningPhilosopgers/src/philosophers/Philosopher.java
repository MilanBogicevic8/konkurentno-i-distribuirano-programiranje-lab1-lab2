package philosophers;

public class Philosopher {

	public static void main(String[] args) {
		NetDP dp1=new NetDP();
		int port=4000;
		String name="localhost";
		RmiDP rmi=new RmiDP();
		
		System.out.println("cao");
		if(!dp1.init(name, port))
			return;
		
		if(!rmi.init(name, port+1))
			return;
		
		System.out.println("cao");
		int id=dp1.getID("milan");
		
		if(id==-1) {
			return;
		}
		System.out.println("cao");
		System.out.println("Moj id:"+id);
		int br=(int)(Math.random()*32);
		int i=0;
		while(true) {
			
			dp1.sit("milan", id);
			System.out.println("Seo sam, filozof broj:"+id);
			rmi.go("milan", id);
			System.out.println("Filozof:"+id+"je otisao");
			i++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//dp1.close();
	}
}

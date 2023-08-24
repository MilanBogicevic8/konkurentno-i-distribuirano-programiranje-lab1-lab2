package rc;

public class Visitor {

	public static void main(String[] args) {
		int port=Integer.parseInt(args[0]);
		String host=args[1];
		String name=args[2];
		NETRC net=new NETRC();
		
		if(!net.init(host, port)) {
			System.out.println("Neka greska...");
			return;
		}
		System.out.println("ulazim");
		System.out.println("Zapoceta voznja");
		int i=(int)(Math.random()*123456);
		Photo photo=net.startRide(name,"milan"+i);
		System.out.println("usao");
		
		for(int i1=0;i1<photo.getNumLines();i1++) {
			System.out.println("Vozio sam se sa"+photo.readLine());
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		net.endRide(name);
		
	}
}

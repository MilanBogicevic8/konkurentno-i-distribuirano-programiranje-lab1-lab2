package rc;

public class Park {

	public static void main(String[] args) {
		int port=Integer.parseInt(args[0]);
		String host=args[1];
		RMIRC rc=new RMIRC();
		
		if(!rc.init(host, port)) {
			System.out.println("Nije dobra inicijalizacija");
			return;
		}
		
		
		for(int i=2;i<args.length;i++) {
			System.out.println("Park:"+args[i]+"je stigao.");
			rc.rollerRide(args[i]);
			System.out.println("Putnici su se ukrcali");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rc.endRide(args[i]);
			System.out.println("putnici su izasli");
		}
	}
}

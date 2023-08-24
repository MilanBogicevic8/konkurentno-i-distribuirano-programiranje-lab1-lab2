package rw;

public class Writer {

	public static void main(String[] args) {
		String host="localhost";
		int port=4001;
		
		RW rw=new NetRW();
		
		while(true) {
			System.out.println("cao");
			
			System.out.println("cao");
			if(!rw.init(host,port))
				return;
			System.out.println("cao");
			int br=(int)(Math.random()*32);
			
			int i=0;
			//while(i<br) {
				String lin=" "+(Math.random()*12345)+".";
				System.out.println("cao");
				rw.startWrite("milan", lin);
				System.out.println("Upisano");
				rw.endWrite("milan");
				System.out.println("Kraj pisanja");
				i++;
			//}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package rw;

public class Reader {

	public static void main(String[] args) {
		
		RW rw=new RMIRW();
		String host="localhost";
		int port=4002;
		
		if(!rw.init(host,port))
			return;
		
		while(true) {
			Book r=rw.startRead("milan");
			for(int i=0;i<r.getNumLines();i++) {
				System.out.println("Linija"+" "+i+" "+r.readLine());
			}
			rw.endRead("milan");
			r.save("milan");
		}
		
		
	}
}

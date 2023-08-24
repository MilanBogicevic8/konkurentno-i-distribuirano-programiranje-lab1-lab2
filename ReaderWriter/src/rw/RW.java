package rw;


public interface RW {

	/**
	 * Inicijalizacija.
	 * 
	 * @param host
	 * @param port
	 * @return true ako je uspesno, inace false
	 */
	public boolean init(String host, int port);

	/**
	 * Regularan zavrsetak programa.
	 * 
	 * @return true ako je uspesno, inace false
	 */
	public boolean close();

	public void startWrite(String name, String line);

	public void endWrite(String name);
	
	public Book startRead(String name);

	public void endRead(String name);
	
	

}

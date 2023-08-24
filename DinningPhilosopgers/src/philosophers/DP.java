package philosophers;

public interface DP {

	public boolean init(String host,int port);
	public void close();
	public void sit(String name,int id);
	public void go(String name,int id);
	public int getID(String name);
}

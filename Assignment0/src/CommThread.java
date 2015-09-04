
public class CommThread implements Runnable 
{
	private boolean server;
	private String ip;
	
	public CommThread()
	{
		server = true;
		ip= "";
	}
	
	public CommThread(String ip)
	{
		server = false;
		this.ip = ip;
	}
	
	
	@Override
	public void run() 
	{
		if(server) 
			new Server();
		else
		{
			try {
				new Client(ip);
			} catch (Exception e) {
				System.out.println("Server not available on " + ip);
				e.printStackTrace();
			}
		}
	}

}

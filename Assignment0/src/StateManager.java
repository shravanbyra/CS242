/*
 * 	STATE MANAGER
 * 
 */

public class StateManager 
{
	private Communicator communicator;
	private boolean server;
	private StateInfo local, remote;
	private int oldThrottleLocal, oldThrottleRemote;
	
	public StateManager(Communicator c)
	{
		communicator = c;
		server = (c instanceof Server);
		
		oldThrottleLocal = -1;
		oldThrottleRemote = -1;
		
		initLocalState();
		initRemoteState();
	}
	
	private void initLocalState()
	{
		local = new StateInfo(server);
	}
	
	private void initRemoteState()
	{
		remote = new StateInfo(!server);
	}
	
	public void updateRemoteState(StateInfo state)
	{
		remote = state;
	}
	
	
	public StateInfo getLocalState()
	{
		return local;
	}
	
	public StateInfo getRemoteState()
	{
		return remote;
	}
	

	public int getState()
	{
		return local.getState();
	}
	
	public void setState(int state)
	{
		local.setState(state);
	}

	public boolean checkThrottleChange() 
	{
		boolean retval = false;
		
		if(local.getThrottle() != oldThrottleLocal)
		{
			oldThrottleLocal = local.getThrottle();
			retval = true;
		}
		
		if(remote.getThrottle() != oldThrottleRemote)
		{
			oldThrottleRemote = remote.getThrottle();
			retval = true;
		}
		
		return retval;
	}

	public int getNumJobstoTransfer() 
	{
		local.printStateInfo();
		remote.printStateInfo();
		
		double t1 = local.getJobTime()/1000000;
		double t2 = remote.getJobTime()/1000000;
		
		double num = local.getJobs()*t1*remote.getThrottle() 
					- remote.getJobs()*t2*local.getThrottle();
		
		double den = t2*local.getThrottle()
				+ t1*remote.getThrottle();
		
		if(num < 0) num *= -1;
		
		System.out.println("num: " + num);
		System.out.println("den: " + den);
		System.out.println("k: " + (num/den));
		
		return (int) (num/den);
	}
	
	
}

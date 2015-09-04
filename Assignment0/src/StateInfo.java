import java.io.Serializable;
import java.util.ArrayList;


public class StateInfo implements Serializable 
{

	private int pendingJobs, finishedJobs;
	private int jobTime;
	private int throttle;
	private int state;
	private double cpuUtilization;
	
	
	public void printStateInfo(){
		System.out.println("State info:");
		System.out.println("  Pending jobs: " + pendingJobs);
		System.out.println("  Finished jobs: " + pendingJobs);
		System.out.println("  Job time: " + jobTime);
		System.out.println("  Throttle: " + throttle);
		System.out.println("  State: " + Global.STATES[state]);
		System.out.println("  CPU Utilization: " + cpuUtilization + "\n");
	}
	
	public StateInfo(boolean server)
	{
		if(server)
			initServerState();
		else initClientState();
	}
	
	private void initServerState()
	{
		state = Global.STATE_WAITING;
		throttle = Global.DEFAULT_THROTTLE;
		pendingJobs = 0;
		finishedJobs = 0;
		cpuUtilization = 0;
		jobTime = 0;
	}
	
	private void initClientState()
	{
		state = Global.STATE_WAITING;
		throttle = Global.DEFAULT_THROTTLE;
		pendingJobs = 1000;
		finishedJobs = 0;
		cpuUtilization = 0;
		jobTime = 0;
	}

	
	public int getState()
	{
		return state;
	}
	
	public void setState(int state)
	{
		this.state = state;
	}

	public int getJobs()
	{
		return pendingJobs;
	}
	
	public void setJobs(int jobs)
	{
		pendingJobs = jobs;
	}
	
	public int getThrottle()
	{
		return throttle;
	}
	
	public void setThrottle(int t)
	{
		throttle = t;
	}

	public double getCpuUtilization()
	{
		return cpuUtilization;
	}
	
	public void setCpuUtilization(double d)
	{
		cpuUtilization = d;
	}
	
	public void setJobTime(int time)
	{
		jobTime = time;
	}
	
	public int getJobTime()
	{
		return jobTime;
	}
	
	public long calculateTotalTime()
	{
		double time = jobTime/1000000;
		return (long) ((time * pendingJobs)/throttle);
	}

	public void setFinishedJobs(int jobs) 
	{
		finishedJobs = jobs; 
	}
	
	public int getFinishedJobs()
	{
		return finishedJobs;
	}
}

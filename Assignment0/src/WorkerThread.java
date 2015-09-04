import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

public class WorkerThread implements Runnable 
{
	int throttle;
	TransferManager tm;
	StateManager sm;
	Job job;
	
	public WorkerThread(TransferManager tm, StateManager sm,
			Job job, int throttle)
	{
		this.tm = tm;
		this.sm = sm;
		this.job = job;
		this.throttle = throttle;
		Global.jobs++;
		System.out.println("Processed Jobs: " + Global.jobs);
	}
	
	@Override
	public void run() 
	{
		long startTime = System.nanoTime();
		long workTime = 0;
		long sleepTime = 0;
		
		for(int i = 0; i < Global.JOB_SIZE; i++)
		{
			for(int j = 0; j < Global.LOOP_SIZE; j++)
			{
				job.setData(i, job.getData(i) + 1.111111);
			}
		}
		
		workTime = System.nanoTime() - startTime;
		//System.out.println("Worktime: " + workTime);
		int workTimeMs = (int) (workTime/1000000);
		int workTimeNs = (int) (workTime%1000000);
		//System.out.println("  Worktime Breakdown: " + workTimeMs + ", "+ workTimeNs);
		
		double factor = (100 / (double)throttle) - 1;
		//System.out.println("Throttle: " + throttle);
		//System.out.println("  Factor: " + factor);
		
		int sleepTimeMs = (int) (workTimeMs*factor);
		int sleepTimeNs = (int) (workTimeNs*factor);
		
		while(sleepTimeNs > 1000000)
		{
			sleepTimeNs -= 1000000;
			sleepTimeMs += 1;
		}
		
		//System.out.println("Sleep Time Breakdown: " + sleepTimeMs + ", "+ sleepTimeNs);
		
		try {
			Thread.sleep(sleepTimeMs, sleepTimeNs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sleepTime = System.nanoTime() - startTime - workTime;
		
		try {
			sm.getLocalState().setCpuUtilization(HardwareMonitor.getProcessCpuLoad());
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReflectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("CPU: "+ sm.getLocalState().getCpuUtilization());
		
		tm.addFinishedJob(job);
		sm.getLocalState().setFinishedJobs(tm.getNumFinishedJobs());
	}

}

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public abstract class Communicator 
{
	protected StateManager stateManager;
	protected TransferManager transferManager;

	protected ObjectOutputStream outToServer, outToClient, outToTransfer;
	
	protected void initManagers()
	{
		stateManager= new StateManager(this);
		transferManager= new TransferManager(this);
		Global.hardwareMonitor = new HardwareMonitor(stateManager); 
	}
	
	protected void sendState(boolean server) 
	{
		if(server)
		{
			try {
				outToClient.writeObject(stateManager.getLocalState());
				outToClient.reset();
				//System.out.println("Server sent state.");
			} catch (IOException e) {
				System.out.println("Could not send state.");
				System.out.println(e);
			}
		}
		else
		{
			try {
				outToServer.writeObject(stateManager.getLocalState());
				outToServer.reset();
				//System.out.println("Client sent state.");

			} catch (IOException e) {
				System.out.println("Could not send state.");
				System.out.println(e);
			}
		}
			
	}

	protected void sendTransfer(Job job) 
	{
		try {
			outToTransfer.writeObject(job);
			outToTransfer.reset();
			if(job != null)
				System.out.println("Job transferred: id[" + job.getId() + "]");
		}
		catch(IOException e){
			System.out.println("Could not send job.");
		}
	}
	
	protected void doWork(Thread thread, ObjectInputStream inFromState,
							ObjectInputStream inFromTransfer, long time, boolean server) throws Exception
	{
		
		//	Do new work if first job or old job hasn't finished
		if(thread == null || !(thread.isAlive()))
		{
			if(!transferManager.isEmptyJobQueue())
			{
        		WorkerThread workerThread = 
        				new WorkerThread(transferManager, stateManager, transferManager.getJob(),
        									Global.hardwareMonitor.getThrottle());
        		thread = new Thread(workerThread);
        		thread.start();
        		
        		//	Make sure to update number of jobs in queue.
        		stateManager.getLocalState().setJobs(transferManager.getNumJobs());
			}
			else if(stateManager.getRemoteState().getJobs() == 0)
			{
    			stateManager.setState(Global.STATE_AGGREGATING);
    			System.out.println("Jobs: " + Global.jobs);
    			System.out.println("Time: " + (int)(System.currentTimeMillis()-time) + " ms");
			}
		}
		
		//	Exchange state information
		sendState(server);
		stateManager.updateRemoteState((StateInfo)inFromState.readObject());

		//	Check for job transfers

		long localTime = stateManager.getLocalState().calculateTotalTime();
		long remoteTime = stateManager.getRemoteState().calculateTotalTime();
		boolean throttleChanged = stateManager.checkThrottleChange();
		
		//	If I should transfer jobs
		if(localTime > remoteTime && throttleChanged)
		{
			int k = stateManager.getNumJobstoTransfer();
			
			System.out.println("Transferring " + k + " jobs.");
			int jobsSent = 0;
			while(jobsSent < k)
			{								
				if(stateManager.getLocalState().getJobs() == 0) break;
				jobsSent++;
				
    			transferManager.transferJob();
        		stateManager.getLocalState().setJobs(transferManager.getNumJobs());

    			//	Wait for response
        		stateManager.updateRemoteState((StateInfo)inFromState.readObject());

			}
			
			transferManager.sendNull();
		}
		//	If I should receive jobs
		else if(localTime < remoteTime && throttleChanged)
		{
			System.out.println("Receiving jobs.");
			boolean transferring = true;
			
			while(transferring)
			{
				//	Listen for new job
        		Job job1 = getJobFromTransfer(inFromTransfer);
        		
        		//	If client bootstrapping phase is done
        		if(job1 == null) transferring = false;
        		else
        		{          
        			//	Add job
        			transferManager.addJob(job1);
	        		stateManager.getLocalState().setJobs(transferManager.getNumJobs());
	        		
	        		//	Let client know job was received.
	        		this.sendState(server);
        		}
			}
		}
		
	}
	 
	protected void calculateJobTime() 
	{
		double[] testJobs = new double[Global.TEST_CASES];
		long startNanos, timeElapsed;
		
		System.out.println("  CALCULATING JOB TIME...");
		
		startNanos = System.nanoTime();
		
		for(int i = 0; i < Global.TEST_CASES; i++)
		{
			for(int j = 0; j < Global.JOB_SIZE; j++)
				for(int k = 0; k < Global.LOOP_SIZE; k++)
					testJobs[i] += 1.111111;
			
			if(i == Global.IGNORE_CASES)
				startNanos = System.nanoTime();
		}
		
		timeElapsed = System.nanoTime()-startNanos;

		
		stateManager.getLocalState().setJobTime((int)(timeElapsed/Global.TEST_CASES));
		
		System.out.println("Average job time: " + stateManager.getLocalState().getJobTime());
	}
	
	protected void printIfStateChange(int currentState)
	{
    	//	Update state on change
    	int newState = stateManager.getState();
    	
    	if(newState != currentState)
    	{
    		System.out.println("CPU: " + stateManager.getLocalState().getCpuUtilization());
    		System.out.println(Global.STATES[currentState] + " -> " + Global.STATES[newState]);
    	}
	}
	
	protected Job getJobFromTransfer(ObjectInputStream inFromTransfer)
	{
		Job job;
		try {
			job = (Job)inFromTransfer.readObject();
			if(job != null) System.out.println("Job received: id[" + job.getId() + "]");
			return job;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

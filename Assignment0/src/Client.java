import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client extends Communicator//CLOSE YOUR SOCKET - NO
{
	
	public Client(String ip) throws Exception
	{
		initManagers();
		initJobs();
		init(ip);
	}
	
	private void initJobs()
	{
		for(int i = 0; i < Global.INIT_JOBS; i++)
		{
			double[] data = new double[Global.JOB_SIZE];
			
			for(int j = 0; j < Global.JOB_SIZE; j++)
				data[j] = 1.111111;
				
			transferManager.addJob(new Job(i, data));
		}
	}
	
	public void init(String ip) throws Exception
	{	 
		Socket stcSocket = new Socket(ip, Global.PORT_STC);
		Socket ctsSocket = new Socket(ip, Global.PORT_CTS);
		Socket transferSocket = new Socket(ip, Global.PORT_TRANSFER);

		outToServer = new ObjectOutputStream(ctsSocket.getOutputStream());
		outToTransfer = new ObjectOutputStream(transferSocket.getOutputStream());
		
		ObjectInputStream inFromServer = new ObjectInputStream(stcSocket.getInputStream());
		ObjectInputStream inFromTransfer = new ObjectInputStream(transferSocket.getInputStream());
		
		int transferredJobs = 0;
		Thread thread = null;
		int time = 0;

    	System.out.println("CLIENT: \n");
    	
		while(true)
		{		 
			int currentState = stateManager.getState();
	       	
        	switch(currentState)
        	{
        	case Global.STATE_WAITING:
        		
        		//	Listen for bootstrap state for server.
        		stateManager.updateRemoteState((StateInfo)inFromServer.readObject());
        		
        		//	If server is ready for bootstrap, begin bootstrapping
        		if(stateManager.getRemoteState().getState() == Global.STATE_BOOTSTRAPPING)
        			stateManager.setState(Global.STATE_BOOTSTRAPPING);
        		        		
        		break;
        	case Global.STATE_BOOTSTRAPPING:
        		
        		//	Send job
        		if(transferredJobs < Global.INIT_JOBS/2)
        		{
        			transferredJobs++;
        			transferManager.transferJob();
        			stateManager.getLocalState().setJobs(transferManager.getNumJobs());
        		}
        		else
        		{
        			transferManager.sendNull();
        		}
        		
        		//	Wait for response
        		stateManager.updateRemoteState((StateInfo)inFromServer.readObject());

        		//	If server is ready for bootstrap, begin bootstrapping
        		if(stateManager.getRemoteState().getState() == Global.STATE_WORKING)
        		{
        			calculateJobTime();
        			time = (int) System.currentTimeMillis();
        			stateManager.setState(Global.STATE_WORKING);
        		}
        		
        		break;
        	case Global.STATE_WORKING:
        		
        		doWork(thread, inFromServer, inFromTransfer, time, false);
        		
        		break;
        	case Global.STATE_AGGREGATING:
        		//	Listen for new job
        		Job job = getJobFromTransfer(inFromTransfer);
        		
        		//	If server aggregating is done
        		if(job == null)
        		{
        			//	Move to done state
        			stateManager.setState(Global.STATE_DONE);
        			time = (int) System.currentTimeMillis() - time;
        			System.out.println("  Finished Jobs: " + 
        					stateManager.getLocalState().getFinishedJobs());
        		}
        		else
        		{
        			//	Add job to queue        
        			System.out.println("Finished job received: [" + job.getId() + "]");
        			
        			transferManager.addFinishedJob(job);
	        		stateManager.getLocalState().setFinishedJobs(transferManager.getNumFinishedJobs());
	        		
        		}
        		//	Let client know job was received.
        		this.sendState(false);
        		
        		break;
        	case Global.STATE_DONE:
        			System.out.println("Results: ");
        			transferManager.displayFinishedResults();
        		return;
        		
        	default:
        		System.out.println("Server: State is invalid.");
        		return;
        	}
        	
        	printIfStateChange(currentState);
        	
		 }
	 }
}
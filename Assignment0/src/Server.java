
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Communicator
{
	public Server()
	{
		try 
		{
			initManagers();
			init();
		}
		catch(Exception e)
		{
			System.out.println("Server: " + e);
		}
	}
	
	private void init() throws Exception
    {
		Socket stcSocket, ctsSocket, transferSocket;
		ObjectInputStream inFromClient, inFromTransfer;
		ServerSocket stcServerSocket = new ServerSocket(Global.PORT_STC);
		ServerSocket ctsServerSocket = new ServerSocket(Global.PORT_CTS);
		ServerSocket transferServerSocket = new ServerSocket(Global.PORT_TRANSFER);

		//	Waits until a client connects
		stcSocket = stcServerSocket.accept();
		ctsSocket = ctsServerSocket.accept();
		transferSocket = transferServerSocket.accept();

		//	Initializes stream for server output
	    outToClient = new ObjectOutputStream(stcSocket.getOutputStream());
	    	
	    //	Initializes stream for client input
		inFromClient = new ObjectInputStream(ctsSocket.getInputStream());

    	//	Initializes streams for server output
    	inFromTransfer = new ObjectInputStream(transferSocket.getInputStream());
    	outToTransfer = new ObjectOutputStream(transferSocket.getOutputStream());
        
    	Thread thread = null;
    	int time = 0;
    	
    	System.out.println("SERVER: \n");
    	
    	
    	//	Loop indefinitely
        while(true)
        {	
        	int currentState = stateManager.getState();
        	
        	switch(currentState)
        	{
        	case Global.STATE_WAITING:
        		
        		//	Let client know server is ready to bootstrap
        		stateManager.setState(Global.STATE_BOOTSTRAPPING);
        		this.sendState(true);
        		
        		break;
        	case Global.STATE_BOOTSTRAPPING:
        		
        		//	Listen for new job
        		Job job = getJobFromTransfer(inFromTransfer);
        		
        		//	If client bootstrapping phase is done
        		if(job == null)
        		{
        			//	Move to working state
        			calculateJobTime();
        			stateManager.setState(Global.STATE_WORKING);
        			time = (int) System.currentTimeMillis();
        		}
        		else
        		{
        			//	Add job to queue        
        			
        			transferManager.addJob(job);
	        		stateManager.getLocalState().setJobs(transferManager.getNumJobs());
	        		
	        		
        		}
        		//	Let client know job was received.
        		this.sendState(true);
        		
        		break;
        	case Global.STATE_WORKING:
        	
        		doWork(thread, inFromClient, inFromTransfer, time, true);
        		
        		break;
        	case Global.STATE_AGGREGATING:
        		//	Send job
        		
        		if(stateManager.getLocalState().getFinishedJobs() > 0)
        		{
        			transferManager.transferFinishedJob();
        			stateManager.getLocalState().setFinishedJobs(transferManager.getNumFinishedJobs());
        		}
        		else
        		{
        			transferManager.sendNull();
        		}
        		
        		//System.out.println("  Waiting for client response");
        		//	Wait for response
        		stateManager.updateRemoteState((StateInfo)inFromClient.readObject());
        		//System.out.println("  Received for client response");
        		
        		//	If client is ready for done, begin doneing
        		if(stateManager.getRemoteState().getState() == Global.STATE_DONE)
        		{
        			time = (int) System.currentTimeMillis();
        			stateManager.setState(Global.STATE_DONE);
        		}
        		break;
        	case Global.STATE_DONE:
        		System.out.println("Server: Job complete!");
        		return;
        		
        	default:
        		System.out.println("Server: State is invalid.");
        		return;
        	}
        	
        	printIfStateChange(currentState);
        	
        }
     }

}

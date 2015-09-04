public class Global 
{
	public static final int 	TIMER_DELAY = 1000,
			
								PORT_STC = 4321,
								PORT_CTS = 6543,
								PORT_TRANSFER = 8765,
								
								DEFAULT_THROTTLE = 50,
								
								JOB_SIZE = 1024*32,
								INIT_JOBS = 1024;
	

	public static final String 	IP_ADDRESS = "localhost"; 
	

	//	STATES
	public static final int 	STATE_WAITING = 0,	 	//	SERVER: WAITING FOR CLIENT 
														//	CLIENT: WAITING ON SERVER RESPONSE
			
								STATE_BOOTSTRAPPING = 1,//	SERVER: READY FOR TRANSFERS 
														//	CLIENT: SENDING TRANSFERS
								
								STATE_WORKING = 2,		//	SERVER: PROCESSING JOBS
														//	CLIENT: PROCESSING JOBS
								
								STATE_AGGREGATING = 3,	//	SERVER: SENDING DATA
														//	CLIENT: READY FOR DATA
	
								STATE_DONE = 4;			//	SERVER: DATA SENT, JOB DONE
														//	CLIENT: DATA COLLECTED, JOB DONE.
	
	public static final String[] STATES = {"WAITING", "BOOTSTRAPPING", 
											"WORKING", "AGGREGATING", "DONE" };

	
	
	public static final int LOOP_SIZE = 1000;

	//public static final int THRESHOLD_JOBS = 20;

	public static final int TEST_CASES = 50;

	public static final int IGNORE_CASES = 10;
	
	public static HardwareMonitor hardwareMonitor;


	public static int jobs = 0;
								
							
}

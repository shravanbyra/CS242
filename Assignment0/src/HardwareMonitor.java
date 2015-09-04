import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;


public class HardwareMonitor 
{
	int throttle;
	StateManager sm;
	
	public HardwareMonitor(StateManager sm)
	{
		throttle = Global.DEFAULT_THROTTLE;
		this.sm = sm;
	}
	
	public int getThrottle()
	{
		return throttle;
	}
	
	public void setThrottle(int t)
	{
		throttle = t;
		sm.getLocalState().setThrottle(throttle);
	}
	
	public static double getProcessCpuLoad() throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException 
	{

	    MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

	    if (list.isEmpty())     return Double.NaN;

	    Attribute att = (Attribute)list.get(0);
	    Double value  = (Double)att.getValue();

	    if (value == -1.0)      return Double.NaN;  // usually takes a couple of seconds before we get real values

	    return ((int)(value * 1000) / 10.0);        // returns a percentage value with 1 decimal point precision
	}
}

import java.io.Serializable;


public class Job implements Serializable
{
	int id;
	double[] data;
	
	public Job(int id, double[] data)
	{
		this.id = id;
		this.data = data;
	}
	
	public double[] getData()
	{
		return data;
	}
	
	public double getData(int i)
	{
		return data[i];
	}
	
	public int getId()
	{
		return id;
	}

	public void setData(int i, double d) {
		data[i] = d;
	}
}

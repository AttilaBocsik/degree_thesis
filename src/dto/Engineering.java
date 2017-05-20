package dto;

public class Engineering {
	
	private int engineeringId;
	private int engineerId;
	private int serviceId;
	
	public Engineering()
	{		
	}
	
	public Engineering(int engineeringId, int engineerId, int serviceId) 
	{
		super();
		this.engineeringId = engineeringId;
		this.engineerId = engineerId;
		this.serviceId = serviceId;
	}

	public int getEngineeringId()
	{
		return engineeringId;
	}

	public void setEngineeringId(int engineeringId) 
	{
		this.engineeringId = engineeringId;
	}

	public int getEngineerId() 
	{
		return engineerId;
	}

	public void setEngineerId(int engineerId) 
	{
		this.engineerId = engineerId;
	}

	public int getServiceId() 
	{
		return serviceId;
	}

	public void setServiceId(int serviceId) 
	{
		this.serviceId = serviceId;
	}

	@Override
	public String toString() 
	{
		return "Engineering [engineeringId=" + engineeringId + ", engineerId=" + engineerId + ", serviceId=" + serviceId
				+ "]";
	}
}

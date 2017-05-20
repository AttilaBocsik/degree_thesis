package dto;

public class Service {
	
	private int serviceId;
	private String type;
	private int price;
	private String description;
	private int orderId;
	
	public Service() 
	{
	}
	
	public Service(int serviceId, String type, int price, String description, int orderId) 
	{
		super();
		this.serviceId = serviceId;
		this.type = type;
		this.price = price;
		this.description = description;
		this.orderId = orderId;
	}

	public int getServiceId() 
	{
		return serviceId;
	}

	public void setServiceId(int serviceId) 
	{
		this.serviceId = serviceId;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getOrderId() 
	{
		return orderId;
	}

	public void setOrderId(int orderId) 
	{
		this.orderId = orderId;
	}

	@Override
	public String toString() 
	{
		return "Service [serviceId=" + serviceId + ", type=" + type + ", price=" + price + ", description="
				+ description + ", orderId=" + orderId + "]";
	}
}

package dto;

import java.sql.Date;

public class Order {
	private int orderId;
	private int serviceId;
	private String taxNumber;
	private String status;
    private Date startDate;
    private Date endDate;
    
	public Order() 
	{				
	}
	
	public Order(int orderId, int serviceId, String taxNumber, 
			   String status, Date startDate, Date endDate) 
	{	
		super();
		this.orderId = orderId;
		this.serviceId = serviceId;
		this.taxNumber = taxNumber;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getOrderId() 
	{
		return orderId;
	}

	public void setOrderId(int orderId) 
	{
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

	public String getTaxNumber() 
	{
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) 
	{
		this.taxNumber = taxNumber;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public Date getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getEndDate() 
	{
		return endDate;
	}

	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}

	@Override
	public String toString() 
	{
		return "Order [orderId=" + orderId + ", serviceId=" + serviceId + ", taxNumber=" + taxNumber + ", status="
				+ status + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}  
}

package dto;

public class City {
	
	private int zipcode;
    private String oneCity;
    
    public City()
	{		
	}
	
	public City(int zipcode, String oneCity)
	{
		super();
		this.zipcode = zipcode;
		this.oneCity = oneCity;
	}

	public int getZipcode() 
	{
		return zipcode;
	}

	public void setZipcode(int zipcode) 
	{
		this.zipcode = zipcode;
	}

	public String getOneCity() 
	{
		return oneCity;
	}

	public void setOneCity(String oneCity) 
	{
		this.oneCity = oneCity;
	}

	@Override
	public String toString() 
	{
		return "City [zipcode=" + zipcode + ", oneCity=" + oneCity + "]";
	}		
}

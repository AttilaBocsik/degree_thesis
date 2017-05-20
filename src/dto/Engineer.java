package dto;

public class Engineer {
	
	private int engineerId;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private int zipcode;
	private String street;
	private int hauseNumber;
	
	public Engineer() 
	{				
	}
	
	public Engineer(int engineerId, String firstName, String lastName, String phone,
			String email, int zipcode, String street, int hauseNumber) 
	{
		super();
		this.engineerId = engineerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.zipcode = zipcode;
		this.street = street;
		this.hauseNumber = hauseNumber;
	}

	public int getEngineerId() 
	{
		return engineerId;
	}

	public void setEngineerId(int engineerId) 
	{
		this.engineerId = engineerId;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getPhone() 
	{
		return phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public int getZipcode() 
	{
		return zipcode;
	}

	public void setZipcode(int zipcode) 
	{
		this.zipcode = zipcode;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public int getHauseNumber() 
	{
		return hauseNumber;
	}

	public void setHauseNumber(int hauseNumber) 
	{
		this.hauseNumber = hauseNumber;
	}

	@Override
	public String toString() 
	{
		return "Engineer [engineerId=" + engineerId + ", firstName=" + firstName + ", lastName=" + lastName + ", phone="
				+ phone + ", email=" + email + ", zipcode=" + zipcode + ", street=" + street + ", hauseNumber="
				+ hauseNumber + "]";
	}	
}

package dto;

public class Partner {
	private String taxNumber;
	private String name;
	private String address;
	private String accountNumber;
	private String phone;
	private String email;
	
	public Partner() 
	{	
	}
	
	public Partner(String taxNumber, String name, String address, String accountNumber,
			String phone, String email) 
	{
		super();
		this.taxNumber = taxNumber;
		this.name = name;
		this.address = address;
		this.accountNumber = accountNumber;
		this.phone = phone;
		this.email = email;
	}

	public String getTaxNumber() 
	{
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) 
	{
		this.taxNumber = taxNumber;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAccountNumber() 
	{
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
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

	@Override
	public String toString() 
	{
		return "Partner [taxNumber=" + taxNumber + ", name=" + name + ", address=" + address + ", accountNumber="
				+ accountNumber + ", phone=" + phone + ", email=" + email + "]";
	}
}

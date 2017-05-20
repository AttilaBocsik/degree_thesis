package webService;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import dto.City;
import model.AccessCityManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/cityService")
public class CityService {
	private int status = 0;	    
	private String responses = null;
	private String cityManagerResponse = null;
	String city = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All City
	//URL: http://localhost:8080/EngineeringProject/Rest/cityService/city
	@GET
	@Path("/city")
	@Produces("application/json")
	public String city()
	{	
		ArrayList<City> cityList = new ArrayList<City>();
		try
		{
			cityList = new AccessCityManager().getCity();
			Gson gson = new Gson();
			if(!cityList.isEmpty())city = gson.toJson(cityList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return city;
	}
		
	//Get zipcode
	//URL: http://localhost:8080/EngineeringProject/Rest/cityService/city/1 
	@GET
	@Produces("application/json")
	@Path("/city/{zipcode}")
	public String messageCity(@PathParam("zipcode") String zipcode)
	{		
		try 
		{
			ArrayList<City> cityList = null;
			AccessCityManager accesscitymanager= new AccessCityManager();
			cityList = accesscitymanager.getZipcode(zipcode);
			if(!cityList.isEmpty())city=JsonTransformer.CityTransformer(cityList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return city;
	}
			
	//Add new city
	//URL: http://localhost:8080/EngineeringProject/Rest/cityService/city
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/city") 		
	public Response addCity(String payload) 
	{    						 	     		 
		try 
		{
			City city = jtGson.gson().fromJson(payload, City.class);
			cityManagerResponse = AccessCityManager.addCity(city);
			switch(cityManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Empty data\"}";
					break;
				case "successful":
					status = 201;
					responses = "{\"message\":\"Recording is successful.\"}";
					break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"Unable to record!\"}";
					break;
				default: 
			}
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
			return Response
						.status(405)
						.type("application/json")
						.entity("{\"message\":\"Error!\"}")
						.build();
		} 
				
		return Response
					.status(status)
					.type("application/json")
					.entity(responses)
					.build();
	} 
			
	//Upload city
	//URL: http://localhost:8080/EngineeringProject/Rest/cityService/city
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/city")  
	public Response modifyCity(String payload) 
	{    								 			    				 
		try 
		{
			City city = jtGson.gson().fromJson(payload, City.class);
			cityManagerResponse = AccessCityManager.modifyCity(city);
			switch(cityManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Empty data\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"The modification is successful.\"}";
					break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"Modification failed.\"}";
					break;
				default: 
			}
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
			return Response
						.status(405)
						.type("application/json")
						.entity("{\"message\":\"Error!\"}")
						.build();
		}
		return Response
					.status(status)
					.type("application/json")
					.entity(responses)
					.build(); 
	}
		
	//Delete zipcode
	//URL: http://localhost:8080/EngineeringProject/Rest/cityService/city/1
	@DELETE
	@Produces("application/json")
	@Path("/city/{zipcode}")  
	public Response deleteCity(@PathParam("zipcode") String zipcode) 
	{    			 
		try 
		{
			cityManagerResponse = AccessCityManager.deleteById(zipcode);
			switch(cityManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Not " +zipcode+ " zipcode!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"Delete " +zipcode+ " zipcode!\"}";
				    break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +zipcode+ " zipcode!\"}";
					break;
				default:
			}
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
			return Response
						.status(405)
						.type("application/json")
						.entity("{\"message\":\"Error!\"}")
						.build();
		}
		return Response
					.status(status)
					.type("application/json")
					.entity(responses)
					.build(); 
	} 
}

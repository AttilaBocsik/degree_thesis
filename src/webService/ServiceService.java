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
import dto.Service;
import model.AccessServiceManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/serviceService")
public class ServiceService {

	private int status = 0;	    
	private String responses = null;
	private String serviceManagerResponse = null;
	String service = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All service
	//URL: http://localhost:8080/EngineeringProject/Rest/serviceService/service
	@GET
	@Path("/service")
	@Produces("application/json")
	public String service()
	{	
		ArrayList<Service> serviceList = new ArrayList<Service>();
		try
		{
			serviceList = new AccessServiceManager().getService();
			Gson gson = new Gson();
			if(!serviceList.isEmpty())service = gson.toJson(serviceList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return service;
	}
		
	//Get serviceId
	//URL: http://localhost:8080/EngineeringProject/Rest/serviceService/service/1 
	@GET
	@Produces("application/json")
	@Path("/partner/{serviceId}")
	public String messageService(@PathParam("serviceId") String serviceId)
	{		
		try 
		{
			ArrayList<Service> serviceList = null;
			AccessServiceManager accessservicemanager= new AccessServiceManager();
			serviceList = accessservicemanager.getServiceId(serviceId);
			if(!serviceList.isEmpty())service=JsonTransformer.ServiceTransformer(serviceList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return service;
	}
			
	//Add new service
	//URL: http://localhost:8080/EngineeringProject/Rest/serviceService/service
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/service") 		
	public Response addService(String payload) 
	{    						 	     		 
		try 
		{
			Service service = jtGson.gson().fromJson(payload, Service.class);
			serviceManagerResponse = AccessServiceManager.addService(service);
			switch(serviceManagerResponse)
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
			
	//Modify service
	//URL: http://localhost:8080/EngineeringProject/Rest/serviceService/service
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/service")  
	public Response modifyService(String payload) 
	{    								 			    				 
		try 
		{
			Service service = jtGson.gson().fromJson(payload, Service.class);
			serviceManagerResponse = AccessServiceManager.modifyService(service);
			switch(serviceManagerResponse)
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
		
	//Delete service
	//URL: http://localhost:8080/EngineeringProject/Rest/serviceService/service/1
	@DELETE
	@Produces("application/json")
	@Path("/service/{serviceId}")  
	public Response deleteService(@PathParam("serviceId") String serviceId) 
	{    			 
		try 
		{
			serviceManagerResponse = AccessServiceManager.deleteById(serviceId);
			switch(serviceManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Not " +serviceId+ " service id!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"Delete " +serviceId+ " service id!\"}";
				    break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +serviceId+ " service id!\"}";
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

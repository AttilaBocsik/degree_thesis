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
import dto.Engineer;
import model.AccessEngineerManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/engineerService")
public class EngineerService {
	
	private int status = 0;	    
	private String responses = null;
	private String engineerManagerResponse = null;
	String engineer = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All Engineer
	//URL: http://localhost:8080/EngineeringProject/Rest/engineerService/engineer
	@GET
	@Path("/engineer")
	@Produces("application/json")
	public String engineer()
	{	
		ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		try
		{
			engineerList = new AccessEngineerManager().getEngineer();
			Gson gson = new Gson();
			if(!engineerList.isEmpty())engineer = gson.toJson(engineerList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineer;
	}
		
	//Get enginerId
	//URL: http://localhost:8080/EngineeringProject/Rest/engineerService/engineer/1 
	@GET
	@Produces("application/json")
	@Path("/engineer/{engineerId}")
	public String messageEngineer(@PathParam("engineerId") String engineerId)
	{		
		try 
		{
			ArrayList<Engineer> engineerList = null;
			AccessEngineerManager accessengineermanager= new AccessEngineerManager();
			engineerList = accessengineermanager.getEngineerId(engineerId);
			if(!engineerList.isEmpty())engineer=JsonTransformer.EngineerTransformer(engineerList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineer;
	}
			
	//Add new engineer
	//URL: http://localhost:8080/EngineeringProject/Rest/engineerService/engineer
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/engineer") 		
	public Response addEngineer(String payload) 
	{    						 	     		 
		try 
		{
			Engineer engineer = jtGson.gson().fromJson(payload, Engineer.class);
			engineerManagerResponse = AccessEngineerManager.addEngineer(engineer);
			switch(engineerManagerResponse)
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
			
	//Modify engineer
	//URL: http://localhost:8080/EngineeringProject/Rest/engineerService/engineer
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/engineer")  
	public Response modifyEngineer(String payload) 
	{    								 			    				 
		try 
		{
			Engineer engineer = jtGson.gson().fromJson(payload, Engineer.class);
			engineerManagerResponse = AccessEngineerManager.modifyEngineer(engineer);
			switch(engineerManagerResponse)
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
		
	//Delete engineerId
	//URL: http://localhost:8080/EngineeringProject/Rest/engineerService/engineer/1
	@DELETE
	@Produces("application/json")
	@Path("/engineer/{engineerId}")  
	public Response deleteEngineer(@PathParam("engineerId") String engineerId) 
	{    			 
		try 
		{
			engineerManagerResponse = AccessEngineerManager.deleteById(engineerId);
			switch(engineerManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Not " +engineerId+ " engineer!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"Delete " +engineerId+ " engineer!\"}";
				    break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +engineerId+ " engineer!\"}";
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

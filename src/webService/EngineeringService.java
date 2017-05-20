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
import dto.Engineering;
import model.AccessEngineeringManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/engineeringService")
public class EngineeringService {
	
	private int status = 0;	    
	private String responses = null;
	private String engineeringManagerResponse = null;
	String engineering = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All Engineering
	//URL: http://localhost:8080/EngineeringProject/Rest/engineeringService/engineering
	@GET
	@Path("/engineering")
	@Produces("application/json")
	public String engineering()
	{
		
		ArrayList<Engineering> engineeringList = new ArrayList<Engineering>();
		try
		{
			engineeringList = new AccessEngineeringManager().getEngineering();
			Gson gson = new Gson();
			if(!engineeringList.isEmpty())engineering = gson.toJson(engineeringList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineering;
	}
	
	//Get engineeringId
	//URL: http://localhost:8080/EngineeringProject/Rest/engineeringService/engineering/1 
	@GET
	@Produces("application/json")
	@Path("/engineering/{engineeringId}")
	public String messageEngineering(@PathParam("engineeringId") String engineeringId)
	{			
		try 
		{
			ArrayList<Engineering> engineeringList = null;
			AccessEngineeringManager accessengineeringmanager= new AccessEngineeringManager();
			engineeringList = accessengineeringmanager.getEngineeringId(engineeringId);
			if(!engineeringList.isEmpty())engineering=JsonTransformer.EngineeringTransformer(engineeringList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineering;
	}
			
	//Add new engineering
	//URL: http://localhost:8080/EngineeringProject/Rest/engineeringService/engineering
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/engineering")  
	public Response addEngineering(String payload) 
	{    					 		     		 
		try 
		{
			Engineering engineering = jtGson.gson().fromJson(payload, Engineering.class);
			engineeringManagerResponse = AccessEngineeringManager.addEngineering(engineering);
			switch(engineeringManagerResponse)
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
			
	//Modify engineering
	//URL: http://localhost:8080/EngineeringProject/Rest/engineeringService/engineering
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/engineering")  
	public Response modifyEngineering(String payload) 
	{    							     			 
		try 
		{
			Engineering engineering = jtGson.gson().fromJson(payload, Engineering.class);
			engineeringManagerResponse = AccessEngineeringManager.modifyEngineering(engineering);
			switch(engineeringManagerResponse)
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
				
	//Delete engineering
	//URL: http://localhost:8080/EngineeringProject/Rest/engineeringService/engineering/1
	@DELETE
	@Produces("application/json")
	@Path("/engineering/{engineeringId}")  
	public Response deleteEngineering(@PathParam("engineeringId") String engineeringId) 
	{    			 
		try 
		{
			engineeringManagerResponse = AccessEngineeringManager.deleteById(engineeringId);
			switch(engineeringManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"message\":\"Not " +engineeringId+ " engineering!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"message\":\"Delete " +engineeringId+ " engineering!\"}";
					break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +engineeringId+ " engineering!\"}";
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

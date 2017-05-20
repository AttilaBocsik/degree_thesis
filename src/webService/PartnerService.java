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
import dto.Partner;
import model.AccessPartnerManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/partnerService")
public class PartnerService {

	private int status = 0;	    
	private String responses = null;
	private String partnerManagerResponse = null;
	String partner = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All partner
	//URL: http://localhost:8080/EngineeringProject/Rest/partnerService/partner
	@GET
	@Path("/partner")
	@Produces("application/json")
	public String partner()
	{	
		ArrayList<Partner> partnerList = new ArrayList<Partner>();
		try
		{
			partnerList = new AccessPartnerManager().getPartner();
			Gson gson = new Gson();
			if(!partnerList.isEmpty())partner = gson.toJson(partnerList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return partner;
	}
		
	//Get taxNumber
	//URL: http://localhost:8080/EngineeringProject/Rest/partnerService/partner/1 
	@GET
	@Produces("application/json")
	@Path("/partner/{taxNumber}")
	public String messagePartner(@PathParam("taxNumber") String taxNumber)
	{		
		try 
		{
			ArrayList<Partner> partnerList = null;
			AccessPartnerManager accesspartnermanager= new AccessPartnerManager();
			partnerList = accesspartnermanager.getTaxNumber(taxNumber);
			if(!partnerList.isEmpty())partner=JsonTransformer.PartnerTransformer(partnerList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return partner;
	}
			
	//Add new partner
	//URL: http://localhost:8080/EngineeringProject/Rest/partnerService/partner
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/partner") 		
	public Response addPartner(String payload) 
	{    						 	     		 
		try 
		{
			Partner partner = jtGson.gson().fromJson(payload, Partner.class);
			partnerManagerResponse = AccessPartnerManager.addPartner(partner);
			switch(partnerManagerResponse)
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
			
	//Modify partner
	//URL: http://localhost:8080/EngineeringProject/Rest/partnerService/partner
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/partner")  
	public Response modifyPartner(String payload) 
	{    								 			    				 
		try 
		{
			Partner partner = jtGson.gson().fromJson(payload, Partner.class);
			partnerManagerResponse = AccessPartnerManager.modifyPartner(partner);
			switch(partnerManagerResponse)
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
		
	//Delete taxNumber
	//URL: http://localhost:8080/EngineeringProject/Rest/partnerService/partner/1
	@DELETE
	@Produces("application/json")
	@Path("/partner/{taxNumber}")  
	public Response deletePartner(@PathParam("taxNumber") String taxNumber) 
	{    			 
		try 
		{
			partnerManagerResponse = AccessPartnerManager.deleteById(taxNumber);
			switch(partnerManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Not " +taxNumber+ " taxnumber!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"Delete " +taxNumber+ " taxnumber!\"}";
				    break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +taxNumber+ " taxnumber!\"}";
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

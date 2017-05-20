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
import dto.Order;
import model.AccessOrderManager;
import utility.JsonTransformer;
import utility.MyLogger;

@Path("/orderService")
public class OrderService {
	
	private int status = 0;	    
	private String responses = null;
	private String orderManagerResponse = null;
	String order = "{\"message\":\"Empty data\"}";
	JsonTransformer jtGson = new JsonTransformer();
	
	//Get All order
	//URL: http://localhost:8080/EngineeringProject/Rest/orderService/order
	@GET
	@Path("/order")
	@Produces("application/json")
	public String order()
	{	
		ArrayList<Order> orderList = new ArrayList<Order>();
		try
		{
			orderList = new AccessOrderManager().getOrder();
			Gson gson = new Gson();
			if(!orderList.isEmpty())order = gson.toJson(orderList);
		} 
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return order;
	}
		
	//Get orderId
	//URL: http://localhost:8080/EngineeringProject/Rest/orderService/order/1 
	@GET
	@Produces("application/json")
	@Path("/order/{engineerId}")
	public String messageOrder(@PathParam("orderId") String orderId)
	{		
		try 
		{
			ArrayList<Order> orderList = null;
			AccessOrderManager accessordermanager= new AccessOrderManager();
			orderList = accessordermanager.getOrderId(orderId);
			if(!orderList.isEmpty())order=JsonTransformer.OrderTransformer(orderList);
		}
		catch (Exception e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return order;
	}
			
	//Add new order
	//URL: http://localhost:8080/EngineeringProject/Rest/orderService/order
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/order") 		
	public Response addOrder(String payload) 
	{    						 	     		 
		try 
		{
			Order order = jtGson.gson().fromJson(payload, Order.class);
			orderManagerResponse = AccessOrderManager.addOrder(order);
			switch(orderManagerResponse)
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
			
	//Modify order
	//URL: http://localhost:8080/EngineeringProject/Rest/orderService/order
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/order")  
	public Response modifyOrder(String payload) 
	{    								 			    				 
		try 
		{
			Order order = jtGson.gson().fromJson(payload, Order.class);
			orderManagerResponse = AccessOrderManager.modifyOrder(order);
			switch(orderManagerResponse)
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
		
	//Delete orderId
	//URL: http://localhost:8080/EngineeringProject/Rest/orderService/order/1
	@DELETE
	@Produces("application/json")
	@Path("/order/{orderId}")  
	public Response deleteOrder(@PathParam("orderId") String orderId) 
	{    			 
		try 
		{
			orderManagerResponse = AccessOrderManager.deleteById(orderId);
			switch(orderManagerResponse)
			{
				case "nothing":
					status = 404;
					responses = "{\"message\":\"Not " +orderId+ " order!\"}";
					break;
				case "successful":
					status = 204;
					responses = "{\"message\":\"Delete " +orderId+ " order!\"}";
				    break;
				case "unsuccessful":
					status = 500;
					responses = "{\"message\":\"No delete " +orderId+ " order!\"}";
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

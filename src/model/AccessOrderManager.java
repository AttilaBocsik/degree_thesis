package model;

import java.sql.Connection;
import java.util.ArrayList;
import dao.AccessOrder;
import dao.Database;
import dto.Order;
import utility.Transaction;

public class AccessOrderManager {
	
	private static String orderManagerResponse = null;
	private static boolean orderResponse = false;
	static Transaction tran = new Transaction();
				
	public ArrayList<Order> getOrder() throws Exception
	{
		ArrayList<Order> orderList = new ArrayList<Order>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessOrder access = new AccessOrder();
		orderList = access.getOrder(con);
		return orderList;
	}
	
	public ArrayList<Order> getOrderId(String orderId)throws Exception
	{
		ArrayList<Order> orderList = null;		
		Database db= new Database();
		Connection con = db.getConnection();		
		AccessOrder access = new AccessOrder();		
		orderList = access.getOrderId(con,orderId);
		return orderList;
	}
	
	public static String addOrder(Order order) throws Exception
	{											
        if(order == null) 
        {
        	orderManagerResponse = tran.getNothing();
        }      
        else 
        {
        	orderResponse = AccessOrder.add(order);        	
        	if(orderResponse)
        	{
        		orderManagerResponse = tran.getSuccessful();
        	}       	
        	else 
        	{
        		orderManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return orderManagerResponse;
	}
			
	public static String modifyOrder(Order order) throws Exception
	{																
		
        if(order == null) 
        {
        	orderManagerResponse = tran.getNothing();
        }      
        else 
        {
        	orderResponse = AccessOrder.modify(order);       	
        	if(orderResponse)
        	{
        		orderManagerResponse = tran.getSuccessful();
        	}      	
        	else 
        	{
        		orderManagerResponse = tran.getUnsuccessful();
        	}			        	
        }				        	                 
        return orderManagerResponse;
	}
	
	public static String deleteById(String orderId) throws Exception
	{		
        Order order = AccessOrder.findById(orderId);
        
        if(order == null) 
        {
            orderManagerResponse = tran.getNothing();
        }
        else 
        {
            orderResponse = AccessOrder.delete(orderId); 
            if(orderResponse)
            {
            	orderManagerResponse = tran.getSuccessful();		
    		}
            else 
    		{
    			orderManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return orderManagerResponse;
	}
}

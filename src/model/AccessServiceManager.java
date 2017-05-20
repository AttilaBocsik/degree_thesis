package model;

import java.util.ArrayList;
import java.sql.Connection;
import dao.AccessService;
import dao.Database;
import dto.Service;
import utility.Transaction;

public class AccessServiceManager {
	
	private static String serviceManagerResponse = null;
	private static boolean serviceResponse = false;
	static Transaction tran = new Transaction();
		
	public ArrayList<Service> getService() throws Exception
	{
		ArrayList<Service> serviceList = new ArrayList<Service>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessService access = new AccessService();
		serviceList = access.getService(con);
		return serviceList;
	}
				
	public ArrayList<Service> getServiceId(String serviceId)throws Exception
	{
		ArrayList<Service> serviceList = null;	
		Database db= new Database();
		Connection con = db.getConnection();
		AccessService access = new AccessService();
		serviceList = access.getServiceId(con,serviceId);
		return serviceList;
	}
			
	public static String addService(Service service) throws Exception
	{				        
        if(service == null) {
        	serviceManagerResponse = tran.getNothing();
        }       
        else {
        	serviceResponse = AccessService.add(service);
        	
        	if(serviceResponse){
        		serviceManagerResponse = tran.getSuccessful();
        	}	        	
        	else {
        		serviceManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return serviceManagerResponse;
	}
				
				
	public static String modifyService(Service service) throws Exception
	{								        
        if(service == null) {
        	serviceManagerResponse = tran.getNothing();
        }       
        else {
        	serviceResponse = AccessService.modify(service);			        	
        	if(serviceResponse){
        		serviceManagerResponse = tran.getSuccessful();
        	}		        	
        	else {
        		serviceManagerResponse = tran.getUnsuccessful();
        	}
        	
        }				        	                 
        return serviceManagerResponse;
	}
			
	public static String deleteById(String serviceId) throws Exception
	{	
		Service service = AccessService.findById(serviceId);      
        if(service == null) {
        	serviceManagerResponse = tran.getNothing();
        }
        else {
        	serviceResponse = AccessService.delete(serviceId); 
            if(serviceResponse)
            {
            	serviceManagerResponse = tran.getSuccessful();
    		}else 
    		{
    			serviceManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return serviceManagerResponse;
	}
}

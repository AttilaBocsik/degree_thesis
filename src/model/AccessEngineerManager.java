package model;

import java.sql.Connection;
import java.util.ArrayList;
import dao.AccessEngineer;
import dao.Database;
import dto.Engineer;
import utility.Transaction;

public class AccessEngineerManager {
	
	private static String engineerManagerResponse = null;
	private static boolean engineerResponse = false;
	static Transaction tran = new Transaction();
		
	public ArrayList<Engineer> getEngineer() throws Exception
	{
		ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessEngineer access = new AccessEngineer();
		engineerList = access.getEngineer(con);
		return engineerList;
	}
		
	public ArrayList<Engineer> getEngineerId(String engineerId)throws Exception
	{
		ArrayList<Engineer> engineerList = null;	
		Database db= new Database();
		Connection con = db.getConnection();
		AccessEngineer access = new AccessEngineer();
		engineerList = access.getEngineerId(con,engineerId);
		return engineerList;
	}
			
	public static String addEngineer(Engineer engineer) throws Exception
	{				
        if(engineer == null) {
        	engineerManagerResponse = tran.getNothing();
        }
        else {
        	engineerResponse = AccessEngineer.add(engineer);
        	
        	if(engineerResponse){
        		engineerManagerResponse = tran.getSuccessful();
        	}
        	else {
        		engineerManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return engineerManagerResponse;
	}
				
	public static String modifyEngineer(Engineer engineer) throws Exception
	{						
        if(engineer == null) {
        	engineerManagerResponse = tran.getNothing();
        }
        else {
        	engineerResponse = AccessEngineer.modify(engineer);
        	
        	if(engineerResponse){
        		engineerManagerResponse = tran.getSuccessful();
        	}
        	else {
        		engineerManagerResponse = tran.getUnsuccessful();
        	}				        	
        }				        	                 
        return engineerManagerResponse;
	}
			
	public static String deleteById(String engineerId) throws Exception
	{			
		Engineer engineer = AccessEngineer.findById(engineerId);
        
        if(engineer == null) {
        	engineerManagerResponse = tran.getNothing();
        }
        else {
        	engineerResponse = AccessEngineer.delete(engineerId); 
            if(engineerResponse)
            {
            	engineerManagerResponse = tran.getSuccessful();		
    		}else 
    		{
    			engineerManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return engineerManagerResponse;
	}
}

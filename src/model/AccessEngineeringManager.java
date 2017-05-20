package model;

import java.sql.Connection;
import java.util.ArrayList;
import dao.AccessEngineering;
import dao.Database;
import dto.Engineering;
import utility.Transaction;

public class AccessEngineeringManager {
	
	private static String engineeringManagerResponse = null;
	private static boolean engineeringResponse = false;
	static Transaction tran = new Transaction();
	    
	public ArrayList<Engineering> getEngineering() throws Exception
	{
		ArrayList<Engineering> engineeringList = new ArrayList<Engineering>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessEngineering access = new AccessEngineering();
		engineeringList = access.getEngineering(con);
		return engineeringList;
	}
				
	public ArrayList<Engineering> getEngineeringId(String engineeringId)throws Exception
	{
		ArrayList<Engineering> engineeringList = null;	
		Database db= new Database();
		Connection con = db.getConnection();
		AccessEngineering access = new AccessEngineering();
		engineeringList = access.getEngineeringId(con,engineeringId);
		return engineeringList;
	}
			
	public static String addEngineering(Engineering engineering) throws Exception
	{				
        if(engineering == null) {
        	engineeringManagerResponse = tran.getNothing();
        }       
        else {
        	engineeringResponse = AccessEngineering.add(engineering);
        	
        	if(engineeringResponse){
        		engineeringManagerResponse = tran.getSuccessful();
        	}
        	else {
        		engineeringManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return engineeringManagerResponse;
	}
				
	public static String modifyEngineering(Engineering engineering) throws Exception
	{								
	    if(engineering == null) {
	    	engineeringManagerResponse = tran.getNothing();
	    }	    
	    else {
	    	engineeringResponse = AccessEngineering.modify(engineering);
	    	
	    	if(engineeringResponse){
	    		engineeringManagerResponse = tran.getSuccessful();
	    	}
	    	else {
	    		engineeringManagerResponse = tran.getUnsuccessful();
	    	}				        	
	    }				        	                 
	    return engineeringManagerResponse;
	}
			
	public static String deleteById(String engineeringId) throws Exception
	{			
		Engineering engineering = AccessEngineering.findById(engineeringId);
        
        if(engineering == null) {
        	engineeringManagerResponse = tran.getNothing();
        }
        else {
        	engineeringResponse = AccessEngineering.delete(engineeringId); 
            if(engineeringResponse)
            {
            	engineeringManagerResponse = tran.getSuccessful();		
    		}else 
    		{
    			engineeringManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return engineeringManagerResponse;
	}
}

package model;

import java.sql.Connection;
import java.util.ArrayList;
import dao.AccessPartner;
import dao.Database;
import dto.Partner;
import utility.Transaction;

public class AccessPartnerManager {
	
	private static String partnerManagerResponse = null;
	private static boolean partnerResponse = false;
	static Transaction tran = new Transaction();
		
	public ArrayList<Partner> getPartner() throws Exception
	{
		ArrayList<Partner> partnerList = new ArrayList<Partner>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessPartner access = new AccessPartner();
		partnerList = access.getPartner(con);
		return partnerList;
	}
				
	public ArrayList<Partner> getTaxNumber(String taxNumber)throws Exception
	{
		ArrayList<Partner> partnerList = null;	
		Database db= new Database();
		Connection con = db.getConnection();
		AccessPartner access = new AccessPartner();
		partnerList = access.getTaxNumber(con,taxNumber);
		return partnerList;
	}
			
	public static String addPartner(Partner partner) throws Exception
	{					
        if(partner == null) 
        {
        	partnerManagerResponse = tran.getNothing();
        }        
        else 
        {
        	partnerResponse = AccessPartner.add(partner);       	
        	if(partnerResponse)
        	{
        		partnerManagerResponse = tran.getSuccessful();
        	}       	
        	else 
        	{
        		partnerManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return partnerManagerResponse;
	}
					
	public static String modifyPartner(Partner partner) throws Exception
	{							
        if(partner == null) 
        {
        	partnerManagerResponse = tran.getNothing();
        }       
        else 
        {
        	partnerResponse = AccessPartner.modify(partner);        	
        	if(partnerResponse)
        	{
        		partnerManagerResponse = tran.getSuccessful();
        	}       	
        	else 
        	{
        		partnerManagerResponse = tran.getUnsuccessful();
        	}			        	
        }				        	                 
        return partnerManagerResponse;
	}
			
	public static String deleteById(String taxNumber) throws Exception
	{		
        Partner partner = AccessPartner.findById(taxNumber);
        
        if(partner == null) 
        {
            partnerManagerResponse = tran.getNothing();
        }
        else 
        {
            partnerResponse = AccessPartner.delete(taxNumber); 
            if(partnerResponse)
            {
            	partnerManagerResponse = tran.getSuccessful();		
    		}
            else 
    		{
    			partnerManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return partnerManagerResponse;
	}
}

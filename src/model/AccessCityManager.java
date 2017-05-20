package model;

import java.sql.Connection;
import java.util.ArrayList;
import dao.AccessCity;
import dao.Database;
import dto.City;
import utility.Transaction;

public class AccessCityManager {
	
	private static String cityManagerResponse = null;
	private static boolean cityResponse = false;	
	static Transaction tran = new Transaction();
		
	public ArrayList<City> getCity() throws Exception
	{
		ArrayList<City> cityList = new ArrayList<City>();
		Database db = new Database();
		Connection con = db.getConnection();
		AccessCity access = new AccessCity();
		cityList = access.getCity(con);
		return cityList;
	}
		
	public ArrayList<City> getZipcode(String zipcode)throws Exception
	{
		ArrayList<City> cityList = null;	
		Database db= new Database();
		Connection con = db.getConnection();
		AccessCity access = new AccessCity();
		cityList = access.getZipcode(con,zipcode);
		return cityList;
	}
		
	public static String addCity(City city) throws Exception
	{			        
        if(city == null) {
        	cityManagerResponse = tran.getNothing();
        }
        else {
        	cityResponse = AccessCity.add(city);
        	
        	if(cityResponse){
        		cityManagerResponse = tran.getSuccessful();
        	}	        	
        	else {
        		cityManagerResponse = tran.getUnsuccessful();
        	}
        }				        	                 
        return cityManagerResponse;
	}
						
	public static String modifyCity(City city) throws Exception
	{							        
        if(city == null) {
        	cityManagerResponse = tran.getNothing();
        }
        else {
        	cityResponse = AccessCity.modify(city);			        	
        	if(cityResponse){
        		cityManagerResponse = tran.getSuccessful();
        	}		        	
        	else {
        		cityManagerResponse = tran.getUnsuccessful();
        	}
        	
        }				        	                 
        return cityManagerResponse;
	}
		
	public static String deleteById(String zipcode) throws Exception
	{		
		City city = AccessCity.findById(zipcode);      
        if(city == null) {
         cityManagerResponse = tran.getNothing();
        }
        else {
            cityResponse = AccessCity.delete(zipcode); 
            if(cityResponse)
            {
            	cityManagerResponse = tran.getSuccessful();
    		}else 
    		{
    			cityManagerResponse = tran.getUnsuccessful();
    		}          
        }
        return cityManagerResponse;
	}
}

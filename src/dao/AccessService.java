package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Service;
import utility.MyLogger;

public class AccessService{
	
	public ArrayList<Service> getService(Connection con) throws SQLException
	{
		ArrayList<Service> serviceList = new ArrayList<Service>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM service");
		ResultSet rs = stmt.executeQuery();
		try
		{			  
			while(rs.next())
			{
				Service serviceObj = new Service();
				serviceObj.setServiceId(rs.getInt("serviceId"));
				serviceObj.setType(rs.getString("type"));
				serviceObj.setPrice(rs.getInt("price"));
				serviceObj.setDescription(rs.getString("description"));
				serviceObj.setOrderId(rs.getInt("orderId"));
				serviceList.add(serviceObj);
			}
		} 
		catch (SQLException e)
		{	
			MyLogger.log(e);
			e.printStackTrace();			
		}
		return serviceList;	
	}
	
	public ArrayList<Service> getServiceId(Connection con,String serviceId) throws SQLException
	{
		    ArrayList<Service> serviceList = new ArrayList<Service>();		    
			PreparedStatement ps = con.prepareStatement("SELECT * FROM service WHERE serviceId=?");
			ps.setString(1,serviceId);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
			{
				Service serviceObj = new Service();
				serviceObj.setServiceId(rs.getInt("serviceId"));
				serviceObj.setType(rs.getString("type"));
				serviceObj.setPrice(rs.getInt("price"));
				serviceObj.setDescription(rs.getString("description"));
				serviceObj.setOrderId(rs.getInt("orderId"));
				serviceList.add(serviceObj);
			}
		 }
		catch(SQLException e)
		{
			 MyLogger.log(e);
			 e.printStackTrace();
		}
		return serviceList;
	}
				
	public static boolean add(Service service) throws Exception 
	{	        
		Statement statement = null; 
		Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO service "
                    + "(serviceId, type, price, description, orderId) "
                    + "VALUES ('%d', '%s', '%d', '%s', '%d');",
                    service.getServiceId(), service.getType(),
                    service.getPrice(), service.getDescription(),
                    service.getOrderId());	            
            statement.executeUpdate(insertSql);	            
            statement.close();
            con.commit();	                     
            return true;
        } 
        catch ( Exception e ) 
        {
        	MyLogger.log(e);
        	e.printStackTrace();
        }  
		finally
        {
        	if(con != null)
        	{
        		try
        		{
        			con.close();
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return false;
    }
						
	public static boolean modify(Service service) throws Exception 
	{			        
		Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE service "
                    + "SET type = '%s', price = '%d', description = '%s',"
                    + " orderId = '%d' WHERE serviceId LIKE '%d';",
                     service.getType(),service.getPrice(), 
                     service.getDescription(),service.getServiceId(),
                     service.getOrderId());	            
            statement.executeUpdate(updateSql);	            
            statement.close();
            con.commit();			                        
            return true;
        } 
        catch ( Exception e ) 
        {
        	MyLogger.log(e);
        	e.printStackTrace();
        } 
        finally
        {
        	if(con != null)
        	{
        		try
        		{
        			con.close();
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return false;
    }
			
	public static boolean delete(String serviceId)  throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try 
		{
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();           
            String deleteSql = String.format("DELETE FROM service WHERE serviceId LIKE '%s';", serviceId);
            statement.executeUpdate(deleteSql);
            statement.close();
            con.commit();	                       
            return true;
        }
		catch (Exception e)
		{	
			MyLogger.log(e);
			e.printStackTrace();				
		}
		finally
        {
        	if(con != null)
        	{
        		try
        		{
        			con.close();
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        	}
        }
		return false;
	}
						
    public static Service findById(String serviceId) 
    {
        Service service = null;
        Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM service WHERE serviceId LIKE '%s';", serviceId);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                int serviceid = rs.getInt("serviceId");
                service = new Service();
                service.setServiceId(serviceid);
            }
            rs.close();
            statement.close();	            
        } 
        catch ( Exception e ) 
        {
        	MyLogger.log(e);
        	e.printStackTrace();
        }
        finally
        {
        	if(con != null)
        	{
        		try
        		{
        			con.close();
        		}
        		catch (Exception e)
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return service;
    }
}

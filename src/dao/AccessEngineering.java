package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Engineering;
import utility.MyLogger;

public class AccessEngineering {
	
	public ArrayList<Engineering> getEngineering(Connection con) throws SQLException
	{
		ArrayList<Engineering> engineeringList = new ArrayList<Engineering>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM engineering");
		ResultSet rs = stmt.executeQuery();
		try
		{
			while(rs.next())
			{
				Engineering engineeringObj = new Engineering();
				engineeringObj.setEngineeringId(rs.getInt("engineeringId"));
				engineeringObj.setEngineerId(rs.getInt("engineerId"));
				engineeringObj.setServiceId(rs.getInt("serviceId"));
				engineeringList.add(engineeringObj);
			}
		} 
		catch (SQLException e)
		{		
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineeringList;
		
	}
		
	public ArrayList<Engineering> getEngineeringId(Connection con, String engineeringId) throws SQLException
	{
		    ArrayList<Engineering> engineeringList = new ArrayList<Engineering>();		    
			PreparedStatement ps = con.prepareStatement("SELECT * FROM engineering WHERE engineeringId=?");
			ps.setString(1,engineeringId);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
			{
				Engineering engineeringObj = new Engineering();
				engineeringObj.setEngineeringId(rs.getInt("engineeringId"));
				engineeringObj.setEngineerId(rs.getInt("engineerId"));
				engineeringObj.setServiceId(rs.getInt("serviceId"));
				engineeringList.add(engineeringObj);
			}
		 }
		catch(SQLException e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineeringList;
	}
					
	public static boolean add(Engineering engineering) throws Exception 
	{	        
		Statement statement = null;
		Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO engineering "
                    + "(engineeringId, engineerId, serviceId) "
                    + "VALUES ('%d', '%d', '%d');",
                    engineering.getEngineeringId(), engineering.getEngineerId(), engineering.getServiceId());	            
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
		
	public static boolean modify(Engineering engineering) throws Exception 
	{			        
		Statement statement = null;
		Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE engineering "
                    + "SET engineerId = '%d', serviceId = '%d',"			                   
                    + "WHERE engineeringId LIKE '%d';",
                    engineering.getEngineerId(), engineering.getServiceId(), engineering.getEngineeringId());	            
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
	
	public static boolean delete(String engineeringId)  throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try 
		{
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();
            String deleteSql = String.format("DELETE FROM engineering WHERE engineeringId LIKE '%s';", engineeringId);
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
		
    public static  Engineering findById(String engineeringId) 
    {
    	Engineering engineering = null;
        Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM engineering WHERE engineeringId LIKE '%s';", engineeringId);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                int engineeringidL = rs.getInt("engineeringId");
                engineering = new Engineering();
                engineering.setEngineeringId(engineeringidL);
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
        return engineering;
    }
}

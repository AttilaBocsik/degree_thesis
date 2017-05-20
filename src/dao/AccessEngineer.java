package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Engineer;
import utility.MyLogger;

public class AccessEngineer {
	
	public ArrayList<Engineer> getEngineer(Connection con) throws SQLException
	{
		ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM engineers");
		ResultSet rs = stmt.executeQuery();
		try
		{
			while(rs.next())
			{
				Engineer engineerObj = new Engineer();
				engineerObj.setEngineerId(rs.getInt("engineerId"));
				engineerObj.setFirstName(rs.getString("firstname"));
				engineerObj.setLastName(rs.getString("lastname"));
				engineerObj.setPhone(rs.getString("phone"));
				engineerObj.setEmail(rs.getString("email"));
				engineerObj.setZipcode(rs.getInt("zipcode"));
				engineerObj.setStreet(rs.getString("street"));
				engineerObj.setHauseNumber(rs.getInt("hausenumber"));
				engineerList.add(engineerObj);
			}
		} 
		catch (SQLException e)
		{	
			MyLogger.log(e);
			e.printStackTrace();
		}
		return engineerList;
		
	}
	
	
	public ArrayList<Engineer> getEngineerId(Connection con,String engineerId) throws SQLException
	{
		    ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
		    
			PreparedStatement ps = con.prepareStatement("SELECT * FROM engineers WHERE engineerId=?");
			ps.setString(1,engineerId);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
				{
				Engineer engineerObj = new Engineer();
				engineerObj.setEngineerId(rs.getInt("engineerId"));
				engineerObj.setFirstName(rs.getString("firstname"));
				engineerObj.setLastName(rs.getString("lastname"));
				engineerObj.setPhone(rs.getString("phone"));
				engineerObj.setEmail(rs.getString("email"));
				engineerObj.setZipcode(rs.getInt("zipcode"));
				engineerObj.setStreet(rs.getString("street"));
				engineerObj.setHauseNumber(rs.getInt("hausenumber"));
				engineerList.add(engineerObj);
				}
		 }
		 catch(SQLException e)
		 {
			 MyLogger.log(e);
			 e.printStackTrace();
		 }
		return engineerList;
	}
			
	public static boolean add(Engineer engineer) throws Exception 
	{	        
		Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO engineers "
                    + "(engineerId, firstname, lastname, phone, email, zipcode, street, hausenumber) "
                    + "VALUES ('%d', '%s', '%s', '%s', '%s', '%d', '%s', '%d');",
                    engineer.getEngineerId(), engineer.getFirstName(), engineer.getLastName(),
                    engineer.getPhone(),engineer.getEmail(), engineer.getZipcode(),
                    engineer.getStreet(), engineer.getHauseNumber());	            
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
        		catch ( Exception e )
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return false;
    }
				
	public static boolean modify(Engineer engineer)  throws Exception 
	{			        
		Statement statement = null;
        Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE engineers "
                    + "SET firstname = '%s', lastname = '%s', phone = '%s',"
                    + "email = '%s', zipcode = '%d', street = '%s', hausenumber = '%d'"
                    + "WHERE engineerId LIKE '%d';",
		            engineer.getFirstName(), engineer.getLastName(),
	                engineer.getPhone(),engineer.getEmail(), engineer.getZipcode(),
	                engineer.getStreet(), engineer.getHauseNumber(),engineer.getEngineerId());
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
        		catch ( Exception e )
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return false;
    }
		
	public static boolean delete(String engineerId)  throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try {
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();
            String deleteSql = String.format("DELETE FROM engineers WHERE engineerId LIKE '%s';", engineerId);
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
        		catch ( Exception e )
        		{
        			e.printStackTrace();
        		}
        	}
        }
		return false;
	}
		
    public static Engineer findById(String engineerId) 
    {
        Engineer engineer = null;
        Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM engineers WHERE engineerId LIKE '%s';", engineerId);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                int engineerid = rs.getInt("engineerId");
                engineer = new Engineer();
                engineer.setEngineerId(engineerid);
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
        		catch ( Exception e )
        		{
        			e.printStackTrace();
        		}
        	}
        }
        return engineer;
    }
}

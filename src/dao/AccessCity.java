package dao;

import utility.MyLogger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dto.City;

public class AccessCity {
	
	public ArrayList<City> getCity(Connection con) throws SQLException
	{
		ArrayList<City> cityList = new ArrayList<City>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM city");
		ResultSet rs = stmt.executeQuery();
		try
		{
			while(rs.next())
			{
				City cityObj = new City();
				cityObj.setZipcode(rs.getInt("zipcode"));
				cityObj.setOneCity(rs.getString("onecity"));				
				cityList.add(cityObj);
			}
		} 
		catch (SQLException e)
		{	
			MyLogger.log(e);
			e.printStackTrace();
		}
		return cityList;		
	}
	
	public ArrayList<City> getZipcode(Connection con,String zipcode) throws SQLException
	{
		    ArrayList<City> cityList = new ArrayList<City>();	    
			PreparedStatement ps = con.prepareStatement("SELECT * FROM city WHERE zipcode=?");
			ps.setString(1,zipcode);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
			{
				City cityObj = new City();
				cityObj.setZipcode(rs.getInt("zipcode"));
				cityObj.setOneCity(rs.getString("onecity"));				
				cityList.add(cityObj);
			}
		 }
		 catch(SQLException e)
		 {
			 MyLogger.log(e);
			e.printStackTrace();
		 }
		return cityList;
	}
			
	public static boolean add(City city) throws Exception 
	{
		Statement statement = null;
		Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO city "
                    + "(zipcode, onecity) "
                    + "VALUES ('%d', '%s');",
                    city.getZipcode(), city.getOneCity());	            
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
		
	public static boolean modify(City city) throws Exception 
	{			        
		Statement statement = null;
		Connection con = null;
        try
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE city "
                    + "SET onecity = '%s' WHERE zipcode LIKE '%d';",
                    city.getOneCity(),city.getZipcode());	            
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
		
	public static boolean delete(String zipcode)throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try
		{
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();           
            String deleteSql = String.format("DELETE FROM city WHERE zipcode LIKE '%s';", zipcode);
            statement.executeUpdate(deleteSql);
            statement.close();
            con.commit();
            con.close();
            
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
		
    public static City findById(String zipcode) 
    {
        City city = null;
        Statement statement = null;
        Connection con =null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM city WHERE zipcode LIKE '%s';", zipcode);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                int zipcodeL = rs.getInt("zipcode");
                city = new City();
                city.setZipcode(zipcodeL);
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
        return city;
    }
}

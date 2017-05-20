package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Partner;
import utility.MyLogger;

public class AccessPartner {
	
	public ArrayList<Partner> getPartner(Connection con) throws SQLException
	{
		ArrayList<Partner> partnerList = new ArrayList<Partner>();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM partner");
		ResultSet rs = stmt.executeQuery();
		try
		{
			while(rs.next())
			{
				Partner partnerObj = new Partner();
				partnerObj.setTaxNumber(rs.getString("taxnumber"));
				partnerObj.setName(rs.getString("name"));
				partnerObj.setAddress(rs.getString("address"));
				partnerObj.setAccountNumber(rs.getString("accountnumber"));
				partnerObj.setPhone(rs.getString("phone"));
				partnerObj.setEmail(rs.getString("email"));
				partnerList.add(partnerObj);
			}
		} 
		catch (SQLException e)
		{	
			MyLogger.log(e);
			e.printStackTrace();
		}
		return partnerList;
		
	}
		
	public ArrayList<Partner> getTaxNumber(Connection con,String taxNumber) throws SQLException
	{
		    ArrayList<Partner> partnerList = new ArrayList<Partner>();	    
			PreparedStatement ps = con.prepareStatement("SELECT * FROM partner WHERE taxnumber=?");
			ps.setString(1,taxNumber);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
			{
				Partner partnerObj = new Partner();
				partnerObj.setTaxNumber(rs.getString("taxnumber"));
				partnerObj.setName(rs.getString("name"));
				partnerObj.setAddress(rs.getString("address"));
				partnerObj.setAccountNumber(rs.getString("accountnumber"));
				partnerObj.setPhone(rs.getString("phone"));
				partnerObj.setEmail(rs.getString("email"));
				partnerList.add(partnerObj);
			}
		 }
		catch(SQLException e)
		{
			MyLogger.log(e);
			e.printStackTrace();
		}
		return partnerList;
	}
		
	public static boolean add(Partner partner) throws Exception 
	{	        
		Statement statement = null;
		Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO partner "
                    + "(taxnumber, name, address, accountnumber, phone, email) "
                    + "VALUES ("
                    +"'%s', '%s', '%s','%s','%s', '%s');",
                    partner.getTaxNumber(), partner.getName(), partner.getAddress(),
                    partner.getAccountNumber(),partner.getPhone(), partner.getEmail());	            
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
		
	public static boolean modify(Partner partner) throws Exception 
	{		        
		Statement statement = null;	
		Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE partner "
                    + "SET name = '%s', address = '%s',"
                    + "accountnumber = '%s', phone = '%s', email = '%s'"
                    + "WHERE taxnumber LIKE '%s';",
					partner.getName(), partner.getAddress(),partner.getAccountNumber(),
					partner.getPhone(), partner.getEmail(),partner.getTaxNumber());	      	            
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
			
			
	public static boolean delete(String taxNumber)  throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try {
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();
            String deleteSql = String.format("DELETE FROM partner WHERE taxnumber LIKE '%s';", taxNumber);
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
		
    public static Partner findById(String taxNumber) {
        Partner partner = null;
        Statement statement = null;
        Connection con = null;
        try {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM partner WHERE taxnumber LIKE '%s';", taxNumber);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                String taxNumberL = rs.getString("taxnumber");
                partner = new Partner();
                partner.setTaxNumber(taxNumberL);
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
        return partner;
    }

}

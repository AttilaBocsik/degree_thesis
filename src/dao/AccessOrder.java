package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dto.Order;
import utility.MyLogger;

public class AccessOrder {
	
	public ArrayList<Order> getOrder(Connection con) throws SQLException
	{
		ArrayList<Order> orderList = new ArrayList<Order>();
		 
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM order");
		ResultSet rs = stmt.executeQuery();
		try
		{
			while(rs.next())
			{			
				Order orderObj = new Order();				
				orderObj.setOrderId(rs.getInt("orderId"));
				orderObj.setServiceId(rs.getInt("serviceId"));
				orderObj.setTaxNumber(rs.getString("taxnumber"));
				orderObj.setStatus(rs.getString("status"));
				orderObj.setStartDate(rs.getDate("startdate"));
				orderObj.setEndDate(rs.getDate("enddate"));				
				orderList.add(orderObj);
			}
		} 
		catch (SQLException e)
		{	
			MyLogger.log(e);
			e.printStackTrace();
		}
		return orderList;		
	}
	
	public ArrayList<Order> getOrderId(Connection con,String orderId) throws SQLException
	{
		    ArrayList<Order> orderList = new ArrayList<Order>();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM order WHERE orderId=?");
			ps.setString(1,orderId);
			ResultSet rs = ps.executeQuery();
		try
		{
			while(rs.next())
			{
				Order orderObj = new Order();				
				orderObj.setOrderId(rs.getInt("orderId"));
				orderObj.setServiceId(rs.getInt("serviceId"));
				orderObj.setTaxNumber(rs.getString("taxnumber"));
				orderObj.setStatus(rs.getString("status"));
				orderObj.setStartDate(rs.getDate("startdate"));
				orderObj.setEndDate(rs.getDate("enddate"));				
				orderList.add(orderObj);
			}
		 }
		 catch(SQLException e)
		 {
			 MyLogger.log(e);
			 e.printStackTrace();
		 }
		return orderList;		
	}
			
	public static boolean add(Order order) throws Exception 
	{        
		Statement statement = null;
		Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String insertSql = String.format("INSERT INTO order "
                    + "(orderId, serviceId, taxnumber, status, startdate, enddate) "
                    + "VALUES ("
                    +"'%d', '%d', '%s','%s','%s', '%s');",
                    order.getOrderId(), order.getServiceId(), order.getTaxNumber(),
                    order.getStatus(),order.getStartDate(), order.getEndDate());	            
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
			
	public static boolean modify(Order order) throws Exception 
	{			        
		Statement statement = null;
		Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);	            
            statement = con.createStatement();	            
            String updateSql = String.format("UPDATE order "
                    + "SET serviceId = '%d', taxnumber = '%s',"
                    + "status = '%s', startdate = '%s', enddate = '%s'"
                    + "WHERE megrendelesId LIKE '%d';",
                     order.getServiceId(), order.getTaxNumber(),order.getStatus(),
                    order.getStartDate(), order.getEndDate(),order.getOrderId());	            
            statement.executeUpdate(updateSql);	            
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
		
	public static boolean delete(String orderId)  throws Exception
	{
		Statement statement = null;
		Connection con = null;
		try 
		{
			Database db= new Database();
			con = db.getConnection();
			con.setAutoCommit(false);
			statement = con.createStatement();
            String deleteSql = String.format("DELETE FROM order WHERE orderId LIKE '%s';", orderId);
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
		
    public static Order findById(String orderId) 
    {
        Order order = null;
        Statement statement = null;
        Connection con = null;
        try 
        {
        	Database db= new Database();
			con = db.getConnection();
            con.setAutoCommit(false);
            statement = con.createStatement();
            String selectSql = String.format("SELECT * "
                    + "FROM order WHERE orderId LIKE '%s';", orderId);
            ResultSet rs = statement.executeQuery(selectSql);           
            while (rs.next()) 
            {
                int orderidL = rs.getInt("orderId");
                order = new Order();
                order.setOrderId(orderidL);
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
        return order;
    }
}

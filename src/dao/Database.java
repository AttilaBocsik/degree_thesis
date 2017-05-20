package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import utility.MyLogger;

public class Database {
	public Connection getConnection() throws Exception 
	{
        try 
        {
        	String driver = "com.mysql.jdbc.Driver";
			String connectionURL = "jdbc:mysql://localhost:3306/engineeringdb?zeroDateTimeBehavior=convertToNull";
			String user = "root";
			String password = "Admin";
			Connection connection = null;
			Class.forName(driver).newInstance();
			connection = DriverManager.getConnection(connectionURL, user, password);
			connection.setAutoCommit(false);
			return connection;
		} 
        catch (Exception e) 
        {
        	MyLogger.log(e);
			throw e;
		}		
	}
}

package dataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	public Connection Get_Connection() throws Exception
	{
		try
		{		
			String connectionURL = "jdbc:mysql://127.0.0.1:3306/kartenverwaltung";
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "");
			return connection;
		}
		catch (SQLException e)
		{
			System.out.println("SQL-Connection error caused!");
			throw e;	
		}
		catch (Exception e)
		{
			System.out.println("Connection error caused!");
			throw e;	
		}
	}

}

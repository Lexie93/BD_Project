package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.ErroreDbException;


public class MyDataSource 
{
	static final private String dbURI = "jdbc:postgresql://localhost/Basi_db";
	static final private String user = "alex93";
	static final private String password = "postgres";
	
	public  synchronized Connection getConnection () throws ErroreDbException 
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			throw new ErroreDbException();
			}
		Connection connection;
		try {
			connection = DriverManager.getConnection(dbURI,user,password);
		} catch (SQLException e) 
		{
			throw new ErroreDbException();
		}
		return connection;
	}
}

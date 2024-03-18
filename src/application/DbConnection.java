package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private String url = "jdbc:sqlserver://localhost:1433;database=LiveAtLund;allowMultiQueries=true";
	private String user = "user";
	private String password = "hej123";
	
	// Method for getting connection to the database
	public Connection getConnection() throws SQLException{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		return DriverManager.getConnection(url, user, password);
	}
}

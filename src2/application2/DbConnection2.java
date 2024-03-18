package application2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection2 {

	private String url = "jdbc:sqlserver://localhost:1433;database=CRONUS;allowMultiQueries=true";
	private String user = "user";
	private String password = "hej123";
	
	public Connection getConnection() throws SQLException{
		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		return DriverManager.getConnection(url, user, password);
	}
}

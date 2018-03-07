package edu.iot.lib.db;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionProvider {
	private static Connection conn;

	public static Properties getDatabaseProperty() throws Exception {

		Properties properties  = new Properties();
		String path = ConnectionProvider.class.getResource(
							"/database.properties").getPath();
		properties.load(new FileReader(path));

		return properties;
	}
	static {
		try {
			Properties properties  = getDatabaseProperty();
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password"); 
			conn = (Connection) DriverManager.getConnection(
									url, username, password);
		} catch (Exception se) {
			System.err.println(se.getMessage());
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	

	
	public static void close() {
		try {
			if(conn != null) { 	conn.close(); }
		} catch (Exception se) { }
	}

}

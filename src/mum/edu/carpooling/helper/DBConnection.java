package mum.edu.carpooling.helper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static Connection connection = null;
	
	public static synchronized Connection getConnection() {
		if (connection != null) {
			return connection;
		} else {
			try {
				InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
				Properties properties = new Properties();
				if (properties != null) {
					properties.load(inputStream);

					String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");

					Class.forName(dbDriver).newInstance();
					connection = DriverManager.getConnection(connectionUrl, userName, password);
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
			}
			return connection;
		}
	}
	
	public static void close() throws SQLException {
		if(connection != null)
			connection.close();
	}
}

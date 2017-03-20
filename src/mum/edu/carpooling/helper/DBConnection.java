package mum.edu.carpooling.helper;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
	private static Connection dbConnection = null;

	public static Connection getConnection() {
		if (dbConnection != null) {
			System.out.println("DB Connection is not null");
			return dbConnection;
		} else {
			System.out.println("Start to connect db");
			try {
				InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
				Properties properties = new Properties();
				if (properties != null) {
					System.out.println("db properties  is OK");
					properties.load(inputStream);
					String dbDriver = properties.getProperty("dbDriver");
					String connectionUrl = properties.getProperty("connectionUrl");
					String userName = properties.getProperty("userName");
					String password = properties.getProperty("password");
					System.out.println(dbDriver + " " + connectionUrl + " " + userName + " " + password);

					Class.forName(dbDriver).newInstance();
					dbConnection = DriverManager.getConnection(connectionUrl, userName, password);
					System.out.println("DB Connection is OK");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
				//e.printStackTrace();
			}
			return dbConnection;
		}
	}
}
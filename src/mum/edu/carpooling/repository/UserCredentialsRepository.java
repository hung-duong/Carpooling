package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mum.edu.carpooling.domain.UserCredentials;
import mum.edu.carpooling.helper.DBConnection;

public class UserCredentialsRepository {
	private Connection connection;

	public UserCredentialsRepository() {
		connection = DBConnection.getConnection();
	}
    
	public boolean authenticate(String username, String password) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("SELECT password FROM usercredentials WHERE username = ?");
			prepStatement.setString(1, username);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					if (result.getString(1).equals(password)) {
						return true;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	public UserCredentials findOne(String username) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("SELECT password FROM usercredentials WHERE username = ?");
			prepStatement.setString(1, username);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					UserCredentials userCredentials = new UserCredentials();
					userCredentials.setPassword(username);
					userCredentials.setPassword(result.getString(1));
					return userCredentials;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void addUser(String username, String password) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("INSERT INTO usercredentials(username, password) VALUES (?, ?)");
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

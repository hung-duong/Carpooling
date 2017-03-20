package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mum.edu.carpooling.helper.DBConnection;

public class UserCredentialsRepository {
	private Connection connection;

	public UserCredentialsRepository() {
		connection = DBConnection.getConnection();
	}

	public boolean authenticate(String username, String password) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("select password from UserCredentials where username = ?");
			prepStatement.setString(1, username);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					if (result.getString(1).equals(password)){
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}
}

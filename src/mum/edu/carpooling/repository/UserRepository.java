package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mum.edu.carpooling.domain.User;
import mum.edu.carpooling.helper.DBConnection;

public class UserRepository {
	private Connection connection;

	public UserRepository() {
		connection = DBConnection.getConnection();
	}

	public void addUserDetails(User user) {
		try {
			String sql = "INSERT INTO users(fullname, gender, email, dob, street, city, state, zipcode, username, datecreated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, curdate())";
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, user.getFullname());
			prepStatement.setInt(2, user.getGender());
			prepStatement.setString(3, user.getEmail());

			java.sql.Date sqlDate = new java.sql.Date(user.getDob().getTime());
			prepStatement.setDate(4, sqlDate);
			prepStatement.setString(5, user.getStreet());
			prepStatement.setString(6, user.getCity());
			prepStatement.setString(7, user.getState());
			prepStatement.setString(8, user.getZipCode());
			prepStatement.setString(9, user.getUsername());

			prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(User newUser) {
		try {
			String sql = "UPDATE users SET fullname = ?,  gender = ?, email = ?, dob = ?, street = ?, city = ?, state = ?, zipcode = ?, dateupdated = curdate() WHERE username = ?";
			PreparedStatement prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, newUser.getFullname());
			prepStatement.setInt(2, newUser.getGender());
			prepStatement.setString(3, newUser.getEmail());

			java.sql.Date sqlDate = new java.sql.Date(newUser.getDob().getTime());
			prepStatement.setDate(4, sqlDate);
			prepStatement.setString(5, newUser.getStreet());
			prepStatement.setString(6, newUser.getCity());
			prepStatement.setString(7, newUser.getState());
			prepStatement.setString(8, newUser.getZipCode());
			prepStatement.setString(9, newUser.getUsername());

			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User findUserByUsername(String username) {
		try {
			PreparedStatement prepStatement = connection.prepareStatement(
					"SELECT fullname, gender, email, dob, street, city, state, zipcode, datecreated, dateupdated FROM users WHERE username = ?");
			prepStatement.setString(1, username);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					User user = new User();
					user.setFullname(result.getString(1));
					user.setGender(result.getInt(2));
					user.setEmail(result.getString(3));
					user.setDob(result.getDate(4));
					user.setStreet(result.getString(5));
					user.setCity(result.getString(6));
					user.setState(result.getString(7));
					user.setZipCode(result.getString(8));
					user.setDatecreated(result.getDate(9));
					user.setDateupdated(result.getDate(10));

					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}

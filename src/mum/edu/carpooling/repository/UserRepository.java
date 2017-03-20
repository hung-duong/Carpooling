package mum.edu.carpooling.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mum.edu.carpooling.domain.User;

public class UserRepository {
	private Connection connection;
	
	public void addUserDetails(User user) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("insert into User(fullname, gender, email, dob, street, city, state, zipCode, datecreated) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			prepStatement.setString(1, user.getFullname());
			prepStatement.setBoolean(2, user.isGender());
			prepStatement.setString(3, user.getEmail());
			
			java.sql.Date sqlDate = new java.sql.Date(user.getDob().getTime());
			prepStatement.setDate(4, sqlDate);
			prepStatement.setString(5, user.getStreet());
			prepStatement.setString(6, user.getCity());
			prepStatement.setString(7, user.getState());
			prepStatement.setString(8, user.getZipCode());
			
			java.util.Date datecreated = new java.util.Date();
			java.sql.Date sqlDatecreated = new java.sql.Date(datecreated.getTime());
			prepStatement.setDate(9, sqlDatecreated);
			
			prepStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateUser(User newUser) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("update User set fullname = ?,  gender = ?, email = ?, dob = ?, street = ?, city = ?, state = ?, zipCode = ?, dateupdated = ? where username = ?");
			prepStatement.setString(1, newUser.getFullname());
			prepStatement.setBoolean(2, newUser.isGender());
			prepStatement.setString(3, newUser.getEmail());
			
			java.sql.Date sqlDate = new java.sql.Date(newUser.getDob().getTime());
			prepStatement.setDate(4, sqlDate);
			prepStatement.setString(5, newUser.getStreet());
			prepStatement.setString(6, newUser.getCity());
			prepStatement.setString(7, newUser.getState());
			prepStatement.setString(8, newUser.getZipCode());
			
			java.util.Date dateupdated = new java.util.Date();
			java.sql.Date sqlDateupdated = new java.sql.Date(dateupdated.getTime());
			prepStatement.setDate(9, sqlDateupdated);
			
			prepStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User findUserByUsername(String username) {
		try {
			PreparedStatement prepStatement = connection
					.prepareStatement("select fullname, gender, email, dob, street, city, state, zipCode, datecreated, dateupdated from User where username = ?");
			prepStatement.setString(1, username);

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				User user = new User();
				
				user.setFullname(result.getString(1));
				user.setGender(result.getBoolean(2));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}

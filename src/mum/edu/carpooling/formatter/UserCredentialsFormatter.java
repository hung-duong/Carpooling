package mum.edu.carpooling.formatter;

import mum.edu.carpooling.domain.UserCredentials;
import org.json.*;


public class UserCredentialsFormatter {
	
	//Parse Json to UserCredentials
	public static UserCredentials parse(String text) {
		JSONObject obj = new JSONObject(text);
		String username = obj.get("username").toString();
		String password = obj.get("password").toString();
		UserCredentials user = new UserCredentials();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
}

package mum.edu.carpooling.service;

import mum.edu.carpooling.domain.UserCredentials;

public interface UserCredentialsService {
	public boolean authenticate(String username, String password);
	public UserCredentials findOne(String username);
	public void addUser(String username, String password);
}

package mum.edu.carpooling.service;

import mum.edu.carpooling.domain.User;

public interface UserService {
	public void addUserDetails(User user);
	public User findUserByUsername(String username);
	public void updateUser(User newUser);
}

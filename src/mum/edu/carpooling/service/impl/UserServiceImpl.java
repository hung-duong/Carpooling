package mum.edu.carpooling.service.impl;

import mum.edu.carpooling.domain.User;
import mum.edu.carpooling.repository.UserRepository;
import mum.edu.carpooling.service.UserService;

public class UserServiceImpl implements UserService {
	UserRepository userResository;

	public UserServiceImpl() {
		userResository = new UserRepository();
	}

	@Override
	public void addUserDetails(User user) {
		userResository.addUserDetails(user);
	}

	public User findUserByUsername(String username) {
		return userResository.findUserByUsername(username);
	}

	public void updateUser(User newUser) {
		userResository.updateUser(newUser);
	}
}

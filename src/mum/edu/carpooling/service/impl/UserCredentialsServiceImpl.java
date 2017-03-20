package mum.edu.carpooling.service.impl;

import mum.edu.carpooling.domain.UserCredentials;
import mum.edu.carpooling.repository.UserCredentialsRepository;
import mum.edu.carpooling.service.UserCredentialsService;

public class UserCredentialsServiceImpl implements UserCredentialsService {
	UserCredentialsRepository userCredentialsRepository;
	
	public UserCredentialsServiceImpl() {
		userCredentialsRepository = new UserCredentialsRepository();
	}
	
	@Override
	public boolean authenticate(String username, String password) {
		return userCredentialsRepository.authenticate(username, password);
	}

	@Override
	public UserCredentials findOne(String username) {
		return userCredentialsRepository.findOne(username);
	}

	public void addUser(String username, String password) {
		userCredentialsRepository.addUser(username, password);
	}
}

package au.edu.usyd.elec5619.service;

import java.security.Principal;

import au.edu.usyd.elec5619.domain.User;

public interface UserService {

	public void createUser(User user);
	
	public void updateUser(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserById(long id);
	
	public boolean isUserExist(User user);
	
	public void deleteUser(String email);
	
	public User getCurrentUser();
	
}


package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.User;

public interface UserService {

	public void createUser(User user);
	
	public void updateUser(User user);
	
	public User getUserByEmail(String email);
	
	public boolean isUserExist(User user);
	
	public boolean validateUser(String email, String password);
	
	public void deleteUser(String email);
	
	public User getCurrentUser();
	
}


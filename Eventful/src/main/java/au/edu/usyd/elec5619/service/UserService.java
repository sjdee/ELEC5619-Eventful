package au.edu.usyd.elec5619.service;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import au.edu.usyd.elec5619.domain.User;

public interface UserService {

	public void createUser(User user);
	
	public User getUserByEmail(String email);
	
	public User getUserById(long id);
	
	public boolean isUserExist(User user);
	
	public void deleteUser(String email);
	
	public User getCurrentUser();
	
	public void changeUserAlias(String newAlias) throws InvalidInputException;
	
	public void changeUserBio(String newBio);
	
	public void changeUserPassword(String oldPassword, String password, String confirmPassword) throws InvalidInputException;
	
	public void changeUserAvatar(String filePath);
}


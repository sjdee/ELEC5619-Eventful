package au.edu.usyd.elec5619.dao;

import java.util.List;

import au.edu.usyd.elec5619.domain.User;

public interface UserDao {
	
	/**
	 * Register user = add user
	 * @param user
	 */
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public List<User> listUsers();
	
	public User getUserById(long id);
	
	public User getUserByEmail(String email);
	
	public List<User> getUsersByAlias(String alias);
	
	public void removeUser(String email);
	
}
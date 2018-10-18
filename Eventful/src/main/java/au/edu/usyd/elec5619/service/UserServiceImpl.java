package au.edu.usyd.elec5619.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.dao.UserDao;
import au.edu.usyd.elec5619.domain.User;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	private UserDao userDAO;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	public void setUserDAO(UserDao userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void createUser(User user) {
		this.userDAO.addUser(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		this.userDAO.updateUser(user);		
	}

	@Override
	@Transactional
	public User getUserByEmail(String email) {
		return this.userDAO.getUserByEmail(email);
	}

	@Override
	@Transactional
	public void deleteUser(String email) {
		this.userDAO.removeUser(email);
	}
	
	@Override
	@Transactional
	public boolean isUserExist(User user) {
		if (getUserByEmail(user.getEmail()) != null) 
			return true;
		return false;
		// TODO:: implementation
	}

	@Override
	@Transactional
	public boolean validateUser(String email, String password) {
		log.info(email + " " + password);

		User user = getUserByEmail(email);
		log.info(user.getEmail() + " " + user.getPassword());
		if (user != null && user.getPassword().equals(password))
			return true;
		return false;
	}
	
	public User getCurrentUser() {
		// TODO:: this needs to be implemented to get the current record from somewhere
		
		String fakeUserEmail = "john@gmail.com";
		
		return userDAO.getUserByEmail(fakeUserEmail);
		
	}
}

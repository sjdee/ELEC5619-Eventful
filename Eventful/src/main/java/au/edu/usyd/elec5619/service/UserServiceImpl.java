package au.edu.usyd.elec5619.service;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.dao.RoleDao;
import au.edu.usyd.elec5619.dao.UserDao;
import au.edu.usyd.elec5619.domain.Role;
import au.edu.usyd.elec5619.domain.User;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	private UserDao userDAO;
	private RoleDao roleDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	public UserServiceImpl(UserDao userDAO, RoleDao roleDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	@Transactional
	public void createUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleDAO.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		this.userDAO.addUser(user);
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
	}

	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		return getUserByEmail(auth.getName());
	}

	@Override
	public User getUserById(long id) {
		return userDAO.getUserById(id);
	}

	@Override
	public void changeUserAlias(String newAlias) throws InvalidInputException {
		log.info(userDAO.getUsersByAlias(newAlias));
		if (newAlias.equals("") || newAlias == null)
			throw new InvalidInputException("Alias cannot be empty.");
		if (userDAO.getUsersByAlias(newAlias).size() != 0)
			throw new InvalidInputException("Alias already exists.");
		userDAO.updateUserAlias(newAlias, getCurrentUser().getId());
	}
	
	@Override
	public void changeUserBio(String newBio) {
		userDAO.updateUserBio(newBio, getCurrentUser().getId());
	}

	@Override
	public void changeUserPassword(String oldPassword, String password, String confirmPassword) throws InvalidInputException {
		password = bCryptPasswordEncoder.encode(password);
		if (!bCryptPasswordEncoder.matches(oldPassword, getCurrentUser().getPassword()))
			throw new InvalidInputException("Invalid Password.");
		else if (bCryptPasswordEncoder.matches(confirmPassword, password))
			userDAO.updateUserPassword(password, getCurrentUser().getId());
		else
			throw new InvalidInputException("Passwords do not match.");
	}

	@Override
	public void changeUserAvatar(String filePath) {
		userDAO.updateUserAvatar(filePath, getCurrentUser().getId());
	}
}

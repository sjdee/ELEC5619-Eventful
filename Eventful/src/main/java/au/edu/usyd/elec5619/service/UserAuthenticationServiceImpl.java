package au.edu.usyd.elec5619.service;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.UserDao;
import au.edu.usyd.elec5619.domain.User;

@Service(value="userAuthService")
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

	private UserDao userDAO;
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	public Long login(String email, String password) {
		log.info(email + " " + password);

		User user = userDAO.getUserByEmail(email);
		log.info(user.getEmail() + " " + user.getPassword());
		if (user != null && user.getPassword().equals(password))
			return user.getId();
		return null;
	}

	@Override
	public Optional<User> findByToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(User user) {
		// TODO Auto-generated method stub
		
	}

}

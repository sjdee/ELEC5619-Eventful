package au.edu.usyd.elec5619.service;

import java.util.Optional;

import au.edu.usyd.elec5619.domain.User;

public interface UserAuthenticationService {
	Long login(String username, String password);
	
	Optional<User> findByToken(String token);
	
	void logout(User user);
}

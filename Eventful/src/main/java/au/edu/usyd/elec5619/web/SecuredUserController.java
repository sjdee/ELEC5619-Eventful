package au.edu.usyd.elec5619.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.service.EventService;
import au.edu.usyd.elec5619.service.UserService;
import ch.qos.logback.classic.Logger;

@Controller
public class SecuredUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;

	protected final Log log = LogFactory.getLog(getClass());

	@Transactional
	@RequestMapping(value = "/profile/{Id}", method=RequestMethod.GET)
	public ModelAndView viewProfile(@PathVariable("Id")long id) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = userService.getUserById(id);
		List<Event> events = eventService.getCreatedEvents(user);

		model.put("events", events);
		model.put("user", user);
		model.put("selfProfile", false);
		
		return new ModelAndView("profile", "model", model);
		
	}
	
	@RequestMapping(value = "/profile", method=RequestMethod.GET)
	public ModelAndView viewProfile() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = userService.getCurrentUser();
		List<Event> events = eventService.getCreatedEvents(user);
		
		System.out.println(events);
		
		model.put("events", events);
		model.put("user", user);
		model.put("selfProfile", true);
		
		return new ModelAndView("profile", "model", model);
		
	}
	
	@RequestMapping(value = "/profile/edit", method=RequestMethod.GET)
	public ModelAndView viewProfileEdit() throws Exception {
		return new ModelAndView("editProfile");
	}

//	@RequestMapping(value = "/{email}", method=RequestMethod.PUT)
//	public ResponseEntity<User> updateUser(@PathVariable("email") String email, @RequestBody User user) {
//		// add log ("Updating User " + email)
//		
//		User currentUser = userService.getUserByEmail(email);
//		
//		if (currentUser == null) {
//			// add log ("User with email " + email + " not found")
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//		
//		currentUser.setEmail(user.getEmail());
//		currentUser.setPassword(user.getPassword());
//		currentUser.setAlias(user.getAlias());
//		currentUser.setAvatar(user.getAvatar());
//		currentUser.setBio(user.getBio());
//		currentUser.setAvgEventRating(user.getAvgEventRating());
//		
//		userService.updateUser(currentUser);
//		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//		
//	}

//	
//	@RequestMapping(value = "/{email}", method=RequestMethod.DELETE)
//	public ResponseEntity<User> deleteUser(@PathVariable("email") String email) {
//		// add log ("Fetching & Deleting User with id " + id)
//		
//		User user = userService.getUserByEmail(email);
//		if (user == null) {
//			// add log ("Unable to delete user with email " + email + " not found")
//			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
//		}
//		
//		userService.deleteUser(email);
//		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//		
//	}
}

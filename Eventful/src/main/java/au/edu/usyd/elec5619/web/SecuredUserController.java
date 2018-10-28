package au.edu.usyd.elec5619.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.service.EventService;
import au.edu.usyd.elec5619.service.UserService;

@Controller
public class SecuredUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	protected final Log log = LogFactory.getLog(getClass());

	@Transactional
	@RequestMapping(value = "/profile/{Id}", method = RequestMethod.GET)
	public ModelAndView viewProfile(@PathVariable("Id") long id) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		User user = userService.getUserById(id);
		List<Event> events = eventService.getCreatedEvents(user);

		model.put("events", events);
		model.put("user", user);
		model.put("selfProfile", false);
		model.put("averageRating", userService.getUserAverageRating(user));

		return new ModelAndView("profile", "model", model);
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView viewProfile() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		User user = userService.getCurrentUser();
		List<Event> events = eventService.getCreatedEvents(user);

		System.out.println(events);

		model.put("events", events);
		model.put("user", user);
		model.put("selfProfile", true);
		model.put("averageRating", userService.getUserAverageRating(user));

		return new ModelAndView("profile", "model", model);
	}

	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public ModelAndView viewProfileEdit() throws Exception {
		return new ModelAndView("editProfile");
	}

	/**
	 * ==============REST API handling for profile updates=====================
	 */

	@RequestMapping(value = "/profile/edit/alias", method = RequestMethod.POST)
	public ResponseEntity<String> updateAlias(HttpServletRequest httpServletRequest) {
		try {
			userService.changeUserAlias(httpServletRequest.getParameter("alias"));
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (InvalidInputException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/profile/edit/bio", method = RequestMethod.POST)
	public ResponseEntity<Void> updateBio(HttpServletRequest httpServletRequest) {
		userService.changeUserBio(httpServletRequest.getParameter("bio"));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/profile/edit/avatar", method = RequestMethod.POST)
	public ResponseEntity<Void> updateAvatar(HttpServletRequest httpServletRequest) {
		userService.changeUserAvatar(httpServletRequest.getParameter("filePath"));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/profile/edit/password", method = RequestMethod.POST)
	public ResponseEntity<String> updatePassword(HttpServletRequest httpServletRequest) {
		try {
			userService.changeUserPassword(httpServletRequest.getParameter("oldPassword"),
					httpServletRequest.getParameter("password"), httpServletRequest.getParameter("confirmPassword"));
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (InvalidInputException e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
		}
	}
}

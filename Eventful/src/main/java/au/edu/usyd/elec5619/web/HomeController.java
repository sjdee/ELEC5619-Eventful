package au.edu.usyd.elec5619.web;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.service.EventService;
import au.edu.usyd.elec5619.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventService eventService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	// TODO:: to be removed
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView viewDashboard(Principal principal) throws Exception {
		String now = (new Date()).toString();
		logger.info("Returning dashboard view at " + now);

		Map<String, Object> model = new HashMap<String, Object>();
		
		User user = userService.getCurrentUser();
		List<Event> events = eventService.getCreatedEvents(user);
		
		System.out.println(events);
		model.put("serverTime", now);
		model.put("events", events);
		model.put("user", user);
		model.put("selfProfile", true);
		
		return new ModelAndView("dashboard", "model", model);
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView showErrorPage() throws Exception {
		String now = (new Date()).toString();
		logger.info("Returning Error page at " + now);

		return new ModelAndView("error");
	}
	
	@RequestMapping(value = "/allEvents", method = RequestMethod.GET)
	public ModelAndView viewAllEvents() throws Exception {
		String now = (new Date()).toString();
		logger.info("Returning all events result view at " + now);

		Map<String, Object> model = new HashMap<String, Object>();
		
		List<Event> events = eventService.getAllEventResults();
		System.out.println(events);
		model.put("serverTime", now);
		model.put("events", events);
		
		return new ModelAndView("allEvents", "model", model);
	}
	
	@RequestMapping(value = "/search={searchQuery}", method = RequestMethod.GET)
	public ModelAndView viewEventResults(@PathVariable("searchQuery") String searchQuery) throws Exception {
		String now = (new Date()).toString();
		logger.info("Returning search result view at " + now);

		Map<String, Object> model = new HashMap<String, Object>();
		List<Event> events = new ArrayList<Event>();
		if (searchQuery == null || searchQuery.isEmpty()) events = eventService.getAllEventResults();		
		else events = eventService.getSearchEventResults(searchQuery);
		
		System.out.println(events);
		model.put("events", events);
		model.put("searchQuery", searchQuery);
		return new ModelAndView("searchResults", "model", model);
	}
	
}
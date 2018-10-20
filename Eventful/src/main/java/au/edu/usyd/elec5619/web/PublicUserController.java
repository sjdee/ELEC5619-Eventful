package au.edu.usyd.elec5619.web;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.service.UserAuthenticationService;
import au.edu.usyd.elec5619.service.UserService;

// http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/
// https://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial

@Controller
//@RequestMapping("/login")
public class PublicUserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAuthenticationService userAuthService;

//	@Autowired
//	private UserSecurityService userSecurityService;

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView viewLogin() throws Exception {
		String now = (new Date()).toString();
		log.info("Returning login view at " + now);
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView viewRegister() throws Exception {
		String now = (new Date()).toString();
		log.info("Returning register view at " + now);
		return new ModelAndView("register", "user", new User());
	}

	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView viewForgotPassword() throws Exception {
		String now = (new Date()).toString();
		log.info("Returning forgot password view at " + now);

		return new ModelAndView("forgot_password");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView submit(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			log.info(result);
			return new ModelAndView("register", "msg", result);
		}

		boolean userExist = userService.isUserExist(user);
		log.info("User " + user.getAlias() + " creation success");

		if (userExist) {
			return new ModelAndView("register", "msg", "User already exist");
		} else {
			userService.createUser(user);
			return new ModelAndView("redirect:/login"); 
		}
	}

}

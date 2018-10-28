package au.edu.usyd.elec5619.web;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.exception.UserNotFoundException;
import au.edu.usyd.elec5619.service.UserService;

@Controller
public class PublicUserController {

	@Autowired
	private UserService userService;
	
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
	public ModelAndView submit(@Valid @ModelAttribute("user") User user, BindingResult result, ModelMap model, HttpServletRequest request) {
		if (result.hasErrors()) {
			log.info(result);
			return new ModelAndView("register", "msg", result);
		}

		boolean userExist = userService.isUserExist(user);

		if (userExist) {
			return new ModelAndView("register", "msg", "User already exist");
		} else {
			userService.createUser(user);
			log.info("User " + user.getAlias() + " creation success");
			return new ModelAndView("redirect:/login");
		}
	}

//	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.POST)
//	public ResponseEntity<String> resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail) {
//		User user = userService.getUserByEmail(userEmail);
//		if (user == null) {
//			throw new UserNotFoundException();
//		}
//		String token = UUID.randomUUID().toString();
//		userService.createPasswordResetTokenForUser(user, token);
//		mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
//		return new GenericResponse(messages.getMessage("message.resetPasswordEmail", null, request.getLocale()));
//	}

}

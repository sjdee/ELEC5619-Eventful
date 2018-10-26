package au.edu.usyd.elec5619.web;

import java.util.Date;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.service.EventService;
import au.edu.usyd.elec5619.service.PostService;
import au.edu.usyd.elec5619.service.UserService;

@Controller
//@RequestMapping(value="/event/**")
@Transactional
public class EventController {

	protected final Log logger = LogFactory.getLog(getClass());

	private EventService eventService;

	private PostService postService;

	private UserService userService;

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	@RequestMapping(value="/createFake/{eventTitle}")
	public ModelAndView createFakeEvent(@PathVariable("eventTitle") String eventTitle) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		User user = new User();

		user.alias = "John";
		user.email = "john@gmail.com";
		user.password = "password";
		user.confirmPassword = "password";

		userService.createUser(user);

		Event event = new Event();

		event.title = eventTitle;
		event.description = "This is a fake event";
		event.location = "Fakeville";
		event.organiser = user;

		eventService.createEvent(event);

		Post post = new Post();

		post.setTitle("Fake post");
		post.setDescription("This is a fake post");
		post.setEvent(event);
		post.setPoster(user);

		postService.createPost(post, event.getId(), user.email);

		Comment comment = new Comment();

		comment.setContents("Fake comment");
		comment.setCommenter(user);
		comment.setPost(post);

		postService.createComment(comment, post.getId(), user.email);

		model.put("event", event);

		return new ModelAndView("createPost", "model", model);
	}

	@RequestMapping(value="/event/{id}")
	public ModelAndView viewEvent(@PathVariable("id") int id, Principal principal) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		Event event = eventService.getEventById(id);
		User user = userService.getUserByEmail(principal.getName());

		model.put("event", event);
		
		int rep = event.getRepetition();
		
		if(rep == 0) {
			model.put("repetition", "Once");
		}
		else if(rep == 1){
			model.put("repetition", "Daily");
		}
		else if(rep == 2){
			model.put("repetition", "Weekly");
		}
		else if(rep == 3){
			model.put("repetition", "Fortnightly");
		}
		else {
			model.put("repetition", "Monthly");
		}
		
		
		//if event is cancelled
		if(event.getCancelled()) {
			model.put("ability", "disabled");
			model.put("buttonValue", "Cancelled");
			model.put("buttonIcon", "cancel");
		}
		
		//if active event
		else {
			
			model.put("ability", "");
			
			//organiser
			//cancel button
			if(event.getOrganiser().getEmail().equals(user.getEmail())) {
				model.put("buttonValue", "Cancel");
				model.put("function", "cancelEvent");
				model.put("buttonIcon", "close");
			}
			// regular user
			//
			else {
				
				//if already subscribed
				if(eventService.checkSubscription(user,event)) {
					model.put("buttonValue", "Unsubscribe");
					model.put("function", "unsubscribe");
					model.put("buttonIcon", "remove_circle");
				}
				//if not yet subscribed
				else {
					model.put("buttonValue", "Subscribe");
					model.put("function", "subscribe");
					model.put("buttonIcon", "add_circle");
				}
			}
		}

		return new ModelAndView("event", "model", model);

	}

	@RequestMapping(value="/createEvent")
	public ModelAndView createEvent() throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("createEvent", "model", model);
	}

	@RequestMapping(value="/createEvent", method=RequestMethod.POST)
	public String createEvent(HttpServletRequest httpServletRequest, Principal principal) throws Exception {

		Event event = new Event();
		User user = userService.getUserByEmail(principal.getName());

		event.setTitle(httpServletRequest.getParameter("title"));
		event.setDescription(httpServletRequest.getParameter("description"));
		event.setMaxPeople(Integer.parseInt(httpServletRequest.getParameter("maxPeople")));
		event.setLocation(httpServletRequest.getParameter("location"));
		event.setRepetition(Integer.parseInt(httpServletRequest.getParameter("repetition")));
		event.setOrganiser(user);
		event.setEventImagePath(httpServletRequest.getParameter("filePath"));

		//formatting date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm a");
		String datetime= httpServletRequest.getParameter("date")+" "+httpServletRequest.getParameter("time");  // departureTime = departureTime + " 00:00:00";
        LocalDateTime date = LocalDateTime.parse(datetime, formatter);
        Date out = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());  
        event.setDatetime(out);
        
        eventService.createEvent(event);
        
//		TODO: Giving issues		
//		eventService.subscribeEvent(user, event);

		return "redirect:/event/" + event.getId();
	}
	
	@RequestMapping(value="/event/cancelEvent/{eventId}", method=RequestMethod.GET)
	public String cancelEvent(@PathVariable("eventId") int eventId,  Principal principal) throws Exception {

		Event event = eventService.getEventById(eventId);
		User user = userService.getUserByEmail(principal.getName());
		
		eventService.unsubscribeEvent(user, event);
		eventService.cancelEvent(user, event);
			
		return "redirect:/event/" + eventId;
		
	}
	
	@RequestMapping(value="/event/subscribe/{eventId}", method=RequestMethod.GET)
	public String subscribe(@PathVariable("eventId") int eventId,  Principal principal) throws Exception {

		Event event = eventService.getEventById(eventId);
		
		String userId = principal.getName();
		
		if(!event.getCancelled()) {
			eventService.subscribeEvent(userService.getUserByEmail(userId), event);
		}
			
		return "redirect:/event/" + eventId;
		
	}
	
	@RequestMapping(value="/event/unsubscribe/{eventId}", method=RequestMethod.GET)
	public String unsubscribe(@PathVariable("eventId") int eventId,  Principal principal) throws Exception {

		Event event = eventService.getEventById(eventId);
		
		String userId = principal.getName();
		eventService.unsubscribeEvent(userService.getUserByEmail(userId), event);
		
		return "redirect:/event/" + eventId;
		
	}
	

	@RequestMapping(value="/createPost/{eventId}")
	public ModelAndView createPost(@PathVariable("eventId") int eventId) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();

		Event event = eventService.getEventById(eventId);

		model.put("event", event);

		return new ModelAndView("createPost", "model", model);
	}

	@RequestMapping(value="/createPost/{eventId}", method=RequestMethod.POST)
	public String createPost(@PathVariable("eventId") int eventId, HttpServletRequest httpServletRequest, Principal principal) throws Exception {


		Post post = new Post();
		post.setTitle(httpServletRequest.getParameter("title"));
		post.setDescription(httpServletRequest.getParameter("description"));
		post.setImagePath(httpServletRequest.getParameter("filePath"));
		
		String userId = principal.getName();

		postService.createPost(post, eventId, userId);

		return "redirect:/event/" + eventId;
	}

	@RequestMapping(value="/createComment/{postId}", method=RequestMethod.POST)
	public String createComment(@PathVariable("postId") int postId, HttpServletRequest httpServletRequest, Principal principal) throws Exception {

		Comment comment = new Comment();
		comment.setContents(httpServletRequest.getParameter("contents"));

		String userId = principal.getName();

		postService.createComment(comment, postId, userId);

		return "redirect:/event/" + comment.getPost().getEvent().getId();
	}

	@RequestMapping(value="/likePost/{postId}", method=RequestMethod.POST)
	public ModelAndView likePost(@PathVariable("postId") int postId, Principal principal) throws Exception {
		logger.info("got here");

		System.out.println(principal.getName());

		String userId = principal.getName();

		postService.likePost(postId, userId);

		Map<String, Object> model = new HashMap<String, Object>();

		Event event = postService.getPostById(postId).getEvent();

		//event.posts = new ArrayList<Post>();

		model.put("event", event);

		return new ModelAndView("redirect:/event/" + event.getId(), "model", model);

	}

	@RequestMapping(value="/likeComment/{commentId}", method=RequestMethod.POST)
	public ModelAndView likeComment(@PathVariable("commentId") int commentId, Principal principal) throws Exception {

		String userId = principal.getName();

		postService.likeComment(commentId, userId);

		Map<String, Object> model = new HashMap<String, Object>();

		Event event = postService.getPostById(postService.getCommentById(commentId).getPost().getId()).getEvent();

		//event.posts = new ArrayList<Post>();

		model.put("event", event);

		return new ModelAndView("redirect:/event/" + event.getId(), "model", model);

	}

}

package au.edu.usyd.elec5619.web;

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
		
		userService.createUser(user);
		
		Event event = new Event();
	
		event.title = eventTitle;
		event.description = "This is a fake event";
		event.location = "Fakeville";
		event.organiser = user;
		
		eventService.createEvent(event);
		
		Post post = new Post();
		
		post.title = "Fake post";
		post.description = "This is a fake post";
		post.event = event;
		post.poster = user;
				
		postService.createPost(post);
		
		Comment comment = new Comment();
		
		comment.contents = "Fake comment";
		comment.commenter = user;
		comment.post = post;

		postService.createComment(comment);
		
		model.put("event", event);
		
		return new ModelAndView("createPost", "model", model); 
	}
	
	@RequestMapping(value="/createPost/{eventId}")
	public ModelAndView createPost(@PathVariable("eventId") int eventId) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Event event = eventService.getEventById(eventId);
		
		model.put("event", event);
		
		return new ModelAndView("createPost", "model", model); 
	}
	
	@RequestMapping(value="/createPost/{eventId}", method=RequestMethod.POST)
	public String createPost(@PathVariable("eventId") int eventId, HttpServletRequest httpServletRequest) throws Exception {				
		
		System.out.println("Event id is -" + eventId + "-");
		
		Post post = new Post();
		post.title = httpServletRequest.getParameter("title");
		post.description = httpServletRequest.getParameter("description");
		
		postService.createPost(post, eventId);
		
		return "redirect:/event/" + eventId;
	}
	
	@RequestMapping(value="/createComment/{postId}", method=RequestMethod.POST)
	public String createComment(@PathVariable("postId") int postId, HttpServletRequest httpServletRequest) throws Exception {				
		
		Comment comment = new Comment();
		comment.contents = httpServletRequest.getParameter("contents");
		
		postService.createComment(comment, postId);
		
		return "redirect:/event/" + comment.post.event.getId();
	}
	
	@RequestMapping(value="/event/{id}")
	public ModelAndView viewEvent(@PathVariable("id") int id) throws Exception {
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		Event event = eventService.getEventById(id);
		
		model.put("event", event);
		
		return new ModelAndView("event", "model", model); 
	}
	
}
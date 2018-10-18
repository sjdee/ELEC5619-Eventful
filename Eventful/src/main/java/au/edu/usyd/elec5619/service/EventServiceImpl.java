package au.edu.usyd.elec5619.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.EventDao;
import au.edu.usyd.elec5619.domain.Event;

@Service(value="eventService")
public class EventServiceImpl implements EventService {
	
	private EventDao eventDao;
	
	private UserService userService;
	
	public static int NUM_POSTS = 5;
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void createEvent(Event event) {
		// TODO Auto-generated method stub
		
		eventDao.createEvent(event);
		
	}

	@Override
	public Event getEventById(int id) {
		// TODO Auto-generated method stub
		
		return eventDao.getEventById(id);
		
	}

}
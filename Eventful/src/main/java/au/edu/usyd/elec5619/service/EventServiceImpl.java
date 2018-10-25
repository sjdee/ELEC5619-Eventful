package au.edu.usyd.elec5619.service;

import javax.transaction.Transactional;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.EventDao;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;

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
	
	@Override
	@Transactional
	public void subscribeEvent(User user, Event event) {
		eventDao.subscribe(user, event);
	}
	
	@Override
	@Transactional
	public void unsubscribeEvent(User user, Event event) {
		eventDao.unsubscribe(user, event);
	}
	
	@Override
	@Transactional
	public boolean checkSubscription(User user, Event event) {
		return eventDao.isSubscribed(user, event);
	}

	@Override
	public List<Event> getCreatedEvents(User user) {
		return eventDao.getEventsByOrganiser(user);
	}
	
	@Override
	public void cancelEvent(User user, Event event){
		eventDao.cancelEvent(user, event);
	}
	
}
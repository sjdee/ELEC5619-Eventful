package au.edu.usyd.elec5619.service;

import java.util.List;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;

public interface EventService {

	public void createEvent(Event event);

	public Event getEventById(int id);
	
	public void subscribeEvent(User user, Event event);

	public List<Event> getCreatedEvents(User user);

	public void unsubscribeEvent(User user, Event event);
	
	public boolean checkSubscription(User user, Event event);
	
	public void cancelEvent(User user, Event event);
	
	public List<Event> getAllEventResults();
	
	public List<Event> getSearchEventResults(String searchQuery);
}

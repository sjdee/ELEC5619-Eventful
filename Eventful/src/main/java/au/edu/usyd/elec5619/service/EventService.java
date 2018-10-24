package au.edu.usyd.elec5619.service;

import java.util.List;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;

public interface EventService {

	public void createEvent(Event event);

	public Event getEventById(int id);
	
	public List<Event> getCreatedEvents(User user);

}

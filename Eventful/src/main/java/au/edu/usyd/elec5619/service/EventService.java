package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;

public interface EventService {

	public void createEvent(Event event);

	public Event getEventById(int id);
	
	public void subscribeEvent(User user, Event event);

	public void unsubscribeEvent(User user, Event event);
	
	public boolean checkSubscription(User user, Event event);
}

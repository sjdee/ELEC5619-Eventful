package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.Event;

public interface EventService {

	public void createEvent(Event event);

	public Event getEventById(int id);

}

package au.edu.usyd.elec5619.dao;

import java.util.List;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;

public interface EventDao {
	
	/**
	 * Register user = add user
	 * @param user
	 */
	public Event getEventById(int id);
		
	public void createEvent(Event event);
	
	public List<Event> getEventsByOrganiser(User user);
		
}

package au.edu.usyd.elec5619.dao;

import au.edu.usyd.elec5619.domain.*;

public interface RatingDao {

	public double getRating(User user, Event event);

	public void insertRating(User user, Event event, double ratingValue);

	public int getRatingId(User user, Event event);
	
	
}
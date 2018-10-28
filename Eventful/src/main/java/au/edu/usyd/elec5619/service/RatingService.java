package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.*;

public interface RatingService {

	public void insertRating(User user, Event event, double ratingValue);
	
	public double getRating(User user, Event event);
		
}
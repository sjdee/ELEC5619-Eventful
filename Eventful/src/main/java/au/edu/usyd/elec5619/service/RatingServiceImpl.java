package au.edu.usyd.elec5619.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.*;
import au.edu.usyd.elec5619.domain.*;

@Transactional
@Service
public class RatingServiceImpl implements RatingService {
	
	private EventDao eventDao;
	
	private RatingDao ratingDao;
	
	private UserService userService;
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void insertRating(User user, Event event, double ratingValue) {
		
		System.out.println("REACHED SERVICE IMPL");
		System.out.println(user);
		System.out.println(event);
		System.out.println(ratingValue);
		ratingDao.insertRating(user, event,ratingValue);
	}
	
	@Override
	public double getRating(User user, Event event) {
		
		return ratingDao.getRating(user, event);
	}
		
}
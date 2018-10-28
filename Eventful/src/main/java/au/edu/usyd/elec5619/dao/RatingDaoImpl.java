package au.edu.usyd.elec5619.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Rating;
import au.edu.usyd.elec5619.domain.Role;
import au.edu.usyd.elec5619.domain.User;

@Transactional
@Repository
public class RatingDaoImpl implements RatingDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public double getRating(User user, Event event) {
		String hql = "FROM Rating r WHERE r.user = ?1 AND r.event = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, user);
		query.setParameter(2, event);
		
		@SuppressWarnings("unchecked")
		List<Rating> list = query.getResultList();
		
		if(list==null || list.isEmpty()) {
			return 0.0;
		}
		else {
			return list.get(0).getRatingValue();
		}
		
	}


	@Override
	public int getRatingId(User user, Event event) {
		String hql = "FROM Rating r WHERE r.user = ?1 AND r.event = ?2";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, user);
		query.setParameter(2, event);
		
		@SuppressWarnings("unchecked")
		List<Rating> list = query.getResultList();
		
		if(list==null || list.isEmpty()) {
			return 0;
		}
		else {
			return list.get(0).getId();	
		}
		
	}
	
	@Override
	public void insertRating(User user, Event event, double ratingValue) {
		
		System.out.println("REACHED DAO IMPL");
		System.out.println(user);
		System.out.println(event);
		System.out.println(ratingValue);
	
//		add rating if it doesnt exist
		if(this.getRating(user, event) == 0.0) {
			Rating rating = new Rating();
			
			rating.setUser(user);
			rating.setEvent(event);
			rating.setRatingValue(ratingValue);
			
			entityManager.persist(rating);
		}
		
//		update Rating
		else{
			
			Rating rating = entityManager.find(Rating.class, this.getRatingId(user, event));
			rating.setRatingValue(ratingValue);
			
			entityManager.persist(rating);
		}
		
	}
}
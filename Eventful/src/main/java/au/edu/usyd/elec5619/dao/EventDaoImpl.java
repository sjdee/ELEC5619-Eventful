package au.edu.usyd.elec5619.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;

@Transactional
@Repository
public class EventDaoImpl implements EventDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Event getEventById(int id) {
		// TODO Auto-generated method stub
//		Session session = this.sessionFactory.getCurrentSession();		
//		Event event = (Event) session.get(Event.class, id);
//		
//		event.posts = null;
//		
//		String hql = "FROM Post P WHERE P.event = " + id + " ORDER BY P.timePosted DESC";
//		
//		Query query = session.createQuery(hql);
//		query.setMaxResults(EventServiceImpl.NUM_POSTS);
//		event.posts = query.list();
//		
//		return event;
		
		return entityManager.find(Event.class, id);
	}
	
	@Override
	public void createEvent(Event event) {
		// TODO Auto-generated method stub
		
//		sessionFactory.getCurrentSession().save(event);
		entityManager.persist(event);
	}
	
	@Override
	public void subscribe(User user, Event event) {
		
		event.getSubscribedUsers().add(user);
		
	}
	
	@Override
	public void unsubscribe(User user, Event event) {
		
		event.getSubscribedUsers().remove(user);
		
	}
	
	@Override
	public boolean isSubscribed(User user, Event event) {
		
		return event.getSubscribedUsers().contains(user);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsByOrganiser(User user) {
		
		String hql = "FROM Event e WHERE e.organiser = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, user);
		
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public void cancelEvent(User user, Event event) {
		
		if(event.getOrganiser().getEmail().equals(user.getEmail())) {
			event.setTitle("[CANCELLED] " + event.getTitle());
			event.setCancelled(true);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> allEvents() {
		String hql = "FROM Event";
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> searchEvents(String searchQuery) {
		
//		String hql = "FROM Event e WHERE e.organiser = ?1";
//		Query query = entityManager.createQuery(hql);
//		query.setParameter(1, user);
		
		String hql = "FROM Event e WHERE (e.title LIKE ?1 OR e.description LIKE ?2 OR e.location LIKE ?3)";
		Query query = entityManager.createQuery(hql);
		String parameter = "%"+searchQuery+"%";
		query.setParameter(1, parameter);
		query.setParameter(2, parameter);
		query.setParameter(3, parameter);
		return query.getResultList();
	}
}

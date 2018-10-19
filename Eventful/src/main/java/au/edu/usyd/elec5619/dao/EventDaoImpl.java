package au.edu.usyd.elec5619.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Post;

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
	
}

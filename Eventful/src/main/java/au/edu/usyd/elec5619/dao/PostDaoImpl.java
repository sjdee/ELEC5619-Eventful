package au.edu.usyd.elec5619.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;

@Transactional
@Service(value="postDao")
public class PostDaoImpl implements PostDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createPost(Post post) {
		
		entityManager.persist(post);
		//sessionFactory.getCurrentSession().save(post);
		
	}
	
	@Override
	public void incrementNumPosts(int eventId) {
		
		String hql = "UPDATE Event e set e.numPosts = e.numPosts + 1 WHERE e.id = ?1";
				
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, eventId);
		
		query.executeUpdate();
						
	}
	
	@Override
	public void incrementNumComments(int postId) {
		
		String hql = "UPDATE Post p set p.numcomments = p.numcomments + 1 WHERE p.id = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, postId);
		
		query.executeUpdate();
						
	}
	
	@Override
	public void createComment(Comment comment) {
				
		entityManager.persist(comment);
		
	}

	@Override
	public boolean getUserLikedComment(int commentId, long userId) {
		
		String likesQuery = "FROM Comment c JOIN c.likedUsers u WHERE c.id = ?1 and u.id = ?2";
		
		Query likeQuery = entityManager.createQuery(likesQuery);
		likeQuery.setParameter(1, commentId);
		likeQuery.setParameter(2, userId);
		
		// If we have already liked the post, return true
		return (likeQuery.getResultList().size() != 0);
	}
	
	@Override
	public void incrementNumCommentLikes(int commentId) {
		// TODO Auto-generated method stub
				
		String hql = "UPDATE Comment c set c.numlikes = c.numlikes + 1 WHERE c.id = ?1";
				
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, commentId);
		
		query.executeUpdate();
	}

	@Override
	public void saveCommentLike(int commentId, long userId) {

		Comment comment = entityManager.find(Comment.class, commentId);
		User user = entityManager.find(User.class, userId);
				
		user.getLikedComments().add(comment);
		
		entityManager.persist(user);
	}

	@Override
	public void savePostLike(int postId, long userId) {

		Post post = entityManager.find(Post.class, postId);
		User user = entityManager.find(User.class, userId);
				
		user.getLikedPosts().add(post);
		
		entityManager.persist(user);
	}
	
	@Override
	public void incrementNumPostLikes(int postId) {
				
		String hql = "UPDATE Post p set p.numLikes = p.numLikes + 1 WHERE p.id = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, postId);
		
		query.executeUpdate();
		
	}
	
	@Override
	public boolean getUserLikedPost(int postId, long userId) {
		
		String likesQuery = "FROM Post p JOIN p.likedUsers u WHERE p.id = ?1 and u.id = ?2";
		
		Query likeQuery = entityManager.createQuery(likesQuery);
		likeQuery.setParameter(1, postId);
		likeQuery.setParameter(2, userId);
		
		// If we have already liked the post
		return (likeQuery.getResultList().size() != 0);
	}
	
	@Override
	public Post getPostById(int id) {
		
		Post post = entityManager.find(Post.class, id);
				
		return post;
	}
	
	@Override
	public Comment getCommentById(int id) {
		Comment comment = entityManager.find(Comment.class, id);
				
		return comment;
	}
	
}
package au.edu.usyd.elec5619.dao;

import java.util.ArrayList;
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

//@Transactional
@Repository
public class PostDaoImpl implements PostDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createPost(Post post) {
		
		entityManager.persist(post);
		//sessionFactory.getCurrentSession().save(post);
		
	}
	
	@Transactional
	@Override
	public List<Post> loadPosts(int eventId, int oldestPostId, int numPosts) {
		
		String hql = "FROM Post p WHERE p.id < ?1 and p.event.id = ?2 order by p.id desc";
		
		Query postQuery = entityManager.createQuery(hql);
		
		if (oldestPostId == -1) {
			postQuery.setParameter(1, Integer.MAX_VALUE);
		} else {
			postQuery.setParameter(1, oldestPostId);
		}
		
		postQuery.setParameter(2, eventId);
		
		postQuery.setMaxResults(numPosts);
		
		return postQuery.getResultList();
	}
	
	//@Transactional
	@Override
	public List<Comment> loadComments(int postId, int oldestCommentId, int numComments) {
		
		
		String hql = "FROM Comment c WHERE c.id < ?1 and c.post.id = ?2 order by c.id desc";
		
		Query commentQuery = entityManager.createQuery(hql);
		
		if (oldestCommentId == -1) {
			commentQuery.setParameter(1, Integer.MAX_VALUE);
		} else {
			commentQuery.setParameter(1, oldestCommentId);
		}
		
		commentQuery.setParameter(2, postId);
		
		commentQuery.setMaxResults(numComments);
		
		return commentQuery.getResultList();
	}
	
	@Transactional
	@Override
	public void incrementNumPosts(int eventId) {
		
		String hql = "UPDATE Event e set e.numPosts = e.numPosts + 1 WHERE e.id = ?1";
				
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, eventId);
		
		query.executeUpdate();
						
	}
	
	@Transactional
	@Override
	public void incrementNumComments(int postId) {
		
		String hql = "UPDATE Post p set p.numComments = p.numComments + 1 WHERE p.id = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, postId);
		
		query.executeUpdate();
						
	}
	
	@Transactional
	@Override
	public void createComment(Comment comment) {
				
		entityManager.persist(comment);
		
	}

	//@Transactional
	@Override
	public boolean getUserLikedComment(int commentId, long userId) {
		
		String likesQuery = "FROM Comment c JOIN c.likedUsers u WHERE c.id = ?1 and u.id = ?2";
		
		Query likeQuery = entityManager.createQuery(likesQuery);
		likeQuery.setParameter(1, commentId);
		likeQuery.setParameter(2, userId);
		
		// If we have already liked the post, return true
		return (likeQuery.getResultList().size() != 0);
	}
	
	@Transactional
	@Override
	public void incrementNumCommentLikes(int commentId) {
		// TODO Auto-generated method stub
				
		String hql = "UPDATE Comment c set c.numLikes = c.numLikes + 1 WHERE c.id = ?1";
				
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, commentId);
		
		query.executeUpdate();
	}

	@Transactional
	@Override
	public void decrementNumCommentLikes(int commentId) {
		// TODO Auto-generated method stub
				
		String hql = "UPDATE Comment c set c.numLikes = c.numLikes - 1 WHERE c.id = ?1";
				
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, commentId);
		
		query.executeUpdate();
	}
	
	@Transactional
	@Override
	public void saveCommentLike(int commentId, long userId) {

		Comment comment = entityManager.find(Comment.class, commentId);
		User user = entityManager.find(User.class, userId);
				
		user.getLikedComments().add(comment);
		
		entityManager.persist(user);
	}
	
	@Transactional
	@Override
	public void removeCommentLike(int commentId, long userId) {

		Comment comment = entityManager.find(Comment.class, commentId);
		User user = entityManager.find(User.class, userId);
		
		user.getLikedComments().remove(comment);
		
		entityManager.persist(user);
	}

	@Transactional
	@Override
	public void savePostLike(int postId, long userId) {

		Post post = entityManager.find(Post.class, postId);
		User user = entityManager.find(User.class, userId);
				
		user.getLikedPosts().add(post);
		
		entityManager.persist(user);
	}
	
	@Transactional
	@Override
	public void removePostLike(int postId, long userId) {

		Post post = entityManager.find(Post.class, postId);
		User user = entityManager.find(User.class, userId);
				
		user.getLikedPosts().remove(post);
		
		entityManager.persist(user);
	}
	
	@Transactional
	@Override
	public void incrementNumPostLikes(int postId) {
				
		String hql = "UPDATE Post p set p.numLikes = p.numLikes + 1 WHERE p.id = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, postId);
		
		query.executeUpdate();
	}
	
	@Transactional
	@Override
	public void decrementNumPostLikes(int postId) {
						
		String hql = "UPDATE Post p set p.numLikes = p.numLikes - 1 WHERE p.id = ?1";
		
		Query query = entityManager.createQuery(hql);
		
		query.setParameter(1, postId);
		
		query.executeUpdate();
		
	}
	
	//@Transactional
	@Override
	public boolean getUserLikedPost(int postId, long userId) {
		
		String likesQuery = "FROM Post p JOIN p.likedUsers u WHERE p.id = ?1 and u.id = ?2";
		
		Query likeQuery = entityManager.createQuery(likesQuery);
		likeQuery.setParameter(1, postId);
		likeQuery.setParameter(2, userId);
		
		// If we have already liked the post
		return (likeQuery.getResultList().size() != 0);
	}
	
	@Transactional
	@Override
	public Post getPostById(int id) {
		
		Post post = entityManager.find(Post.class, id);
				
		return post;
	}
	
	@Transactional
	@Override
	public Comment getCommentById(int id) {
		Comment comment = entityManager.find(Comment.class, id);
				
		return comment;
	}
	
}

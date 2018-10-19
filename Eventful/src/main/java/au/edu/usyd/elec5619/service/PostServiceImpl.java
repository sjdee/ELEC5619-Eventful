package au.edu.usyd.elec5619.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.EventDao;
import au.edu.usyd.elec5619.dao.PostDao;
import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;

@Transactional
@Service
public class PostServiceImpl implements PostService {
	
	private EventDao eventDao;
	
	private PostDao postDao;
	
	private UserService userService;
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void createPost(Post post, int eventId) {
		
		Date date = new Date();

		post.setTimePosted(date);
		
		post.setEvent(eventDao.getEventById(eventId));
		
		post.setPoster(userService.getCurrentUser());
				
		postDao.createPost(post);
				
		postDao.incrementNumPosts(eventId);
		
	}

	@Override
	public void createComment(Comment comment, int postId) {
		// TODO Auto-generated method stub
		
		Date date = new Date();

		comment.setTimePosted(date);
				
		comment.setCommenter(userService.getCurrentUser());
		
		comment.setPost(postDao.getPostById(postId));
				
		postDao.createComment(comment);
		
		postDao.incrementNumComments(postId);
		
	}
	
	@Override
	public void likePost(int postId) {
						
		User currentUser = userService.getCurrentUser();
		
		// If user hasn't liked post
		if (!postDao.getUserLikedPost(postId, currentUser.getId())) {
			
			postDao.incrementNumPostLikes(postId);
			
			postDao.savePostLike(postId,currentUser.getId());
			
		}
				
	}

	@Override
	public Post getPostById(int postId) {
		
		return postDao.getPostById(postId);
		
	}
	
	@Override
	public Comment getCommentById(int commentId) {
		
		return postDao.getCommentById(commentId);
		
	}
	
	@Override
	@Transactional
	public void likeComment(int commentId) {
		
		User currentUser = userService.getCurrentUser();
		
		// If user hasn't liked post
		if (!postDao.getUserLikedComment(commentId, currentUser.getId())) {
			
			postDao.incrementNumCommentLikes(commentId);
			
			postDao.saveCommentLike(commentId, currentUser.getId());
			
		}
		
	}
}
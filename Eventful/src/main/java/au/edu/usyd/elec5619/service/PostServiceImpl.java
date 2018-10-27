package au.edu.usyd.elec5619.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.EventDao;
import au.edu.usyd.elec5619.dao.PostDao;
import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.payload.LikeResponse;

//@Transactional
@Service
public class PostServiceImpl implements PostService {
	
	private EventDao eventDao;
	
	private PostDao postDao;
	
	private UserService userService;
	
	public static int NUM_POSTS = 5;
	
	public static int NUM_COMMENTS = 5;
	
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
	
	@Transactional
	@Override
	public void createPost(Post post, int eventId, String userId) {
		
		Date date = new Date();

		post.setTimePosted(date);
		
		post.setEvent(eventDao.getEventById(eventId));
		
		post.setPoster(userService.getUserByEmail(userId));
				
		postDao.createPost(post);
				
		postDao.incrementNumPosts(eventId);
		
	}

	@Transactional
	@Override
	public void createComment(Comment comment, int postId, String userEmail) {
		// TODO Auto-generated method stub
		
		Date date = new Date();

		comment.setTimePosted(date);
				
		comment.setCommenter(userService.getUserByEmail(userEmail));
		
		comment.setPost(postDao.getPostById(postId));
				
		postDao.createComment(comment);
		
		postDao.incrementNumComments(postId);
		
	}
	
	@Transactional
	@Override
	public LikeResponse likePost(int postId, User user) {
				
		LikeResponse response = new LikeResponse();
		
		Boolean isLiked = postDao.getUserLikedPost(postId, user.getId());
		
		int numLikes = postDao.getPostById(postId).getNumLikes();
		
		// If user hasn't liked post
		if (!isLiked) {
			
			postDao.incrementNumPostLikes(postId);
			
			postDao.savePostLike(postId, user.getId());
			
			numLikes ++;
			
		} else {
			
			postDao.decrementNumPostLikes(postId);
			
			postDao.removePostLike(postId, user.getId());
			
			numLikes --;
		
		}
		
		response.setIsLiked(!isLiked);
		response.setNumLikes(numLikes);
			
		return response;
	}
	
	@Override
	@Transactional
	public LikeResponse likeComment(int commentId, User user) {
		
		LikeResponse response = new LikeResponse();
		
		Boolean isLiked = postDao.getUserLikedComment(commentId, user.getId());
		
		int numLikes = postDao.getCommentById(commentId).getNumLikes();
		
		// If user hasn't liked post
		if (!isLiked) {
			
			postDao.incrementNumCommentLikes(commentId);
			
			postDao.saveCommentLike(commentId, user.getId());
			
			numLikes ++;
			
		} else {
			
			postDao.decrementNumCommentLikes(commentId);
			
			postDao.removeCommentLike(commentId, user.getId());
			
			numLikes --;
		
		}
		
		response.setIsLiked(!isLiked);
		response.setNumLikes(numLikes);
			
		return response;
		
	}
	
	//@Transactional
	@Override
	public Boolean getUserLikedPost(int postId, long userId) {
								
		return postDao.getUserLikedPost(postId, userId);
				
	}
	
	//@Transactional
	@Override
	public Boolean getUserLikedComment(int commentId, long userId) {
								
		return postDao.getUserLikedComment(commentId, userId);
				
	}
	
	@Transactional
	@Override
	public Post getPostById(int postId) {
		
		return postDao.getPostById(postId);
		
	}
	
	@Transactional
	@Override
	public Comment getCommentById(int commentId) {
		
		return postDao.getCommentById(commentId);
		
	}

	@Transactional
	@Override
	public List<Post> loadPosts(int eventId, int oldestPostId) {
		
		List<Post> postList = postDao.loadPosts(eventId, oldestPostId, NUM_POSTS);
		
		return postList;
		
	}
	
	@Override
	public List<Comment> loadComments(int postId, int oldestCommentId) {
		
		List<Comment> commentList = postDao.loadComments(postId, oldestCommentId, NUM_COMMENTS);
		
		return commentList;
		
	}
	
}
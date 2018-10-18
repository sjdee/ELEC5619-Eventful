package au.edu.usyd.elec5619.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.edu.usyd.elec5619.dao.EventDao;
import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;

@Service
public class PostServiceImpl implements PostService {
	
	private EventDao eventDao;
	
	private UserService userService;
	
	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public void createPost(Post post, int eventId) {
		// TODO Auto-generated method stub
		
		Date date = new Date();

		post.timeposted = date;
		
		post.event = eventDao.getEventById(eventId);
		
		post.poster = userService.getCurrentUser();
				
		eventDao.createPost(post);
				
	}

	@Override
	public void createComment(Comment comment, int postId) {
		// TODO Auto-generated method stub
		
		System.out.println("Creating comment from service");
		
		comment.commenter = userService.getCurrentUser();
		
		comment.post = eventDao.getPostById(postId);
				
		eventDao.createComment(comment);
		
	}

	@Override
	public void createPost(Post post) {
		// TODO Auto-generated method stub
		
		Date date = new Date();

		post.timeposted = date;
						
		eventDao.createPost(post);
		
	}

	@Override
	public void createComment(Comment comment) {
		// TODO Auto-generated method stub
								
		eventDao.createComment(comment);
		
	}
	
}
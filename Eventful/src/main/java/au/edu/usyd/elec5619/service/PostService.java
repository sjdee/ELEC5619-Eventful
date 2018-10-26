package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;
import au.edu.usyd.elec5619.payload.LikeResponse;

public interface PostService {

	public void createPost(Post post, int eventId, String userEmail);
	
	public void createComment(Comment comment, int postId, String userEmail);
	
	public LikeResponse likePost(int postId, User user);
	
	public LikeResponse likeComment(int commentId, User user);
	
	public Post getPostById(int postId);
	
	public Comment getCommentById(int commentId);
	
	public Boolean getUserLikedPost(int postId, String userEmail);
	
}

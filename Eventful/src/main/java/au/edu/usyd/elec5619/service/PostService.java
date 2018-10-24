package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;

public interface PostService {

	public void createPost(Post post, int eventId, String userEmail);
	
	public void createComment(Comment comment, int postId, String userEmail);
	
	public void likePost(int postId, String userEmail);
	
	public void likeComment(int commentId, String userEmail);
	
	public Post getPostById(int postId);
	
	public Comment getCommentById(int commentId);
		
}

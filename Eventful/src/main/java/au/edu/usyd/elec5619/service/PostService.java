package au.edu.usyd.elec5619.service;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;

public interface PostService {

	public void createPost(Post post, int eventId);
	
	public void createComment(Comment comment, int postId);
	
	public void likePost(int postId);
	
	public void likeComment(int commentId);
	
	public Post getPostById(int postId);
	
	public Comment getCommentById(int commentId);
		
}

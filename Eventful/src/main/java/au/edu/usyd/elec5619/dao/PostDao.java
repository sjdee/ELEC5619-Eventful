package au.edu.usyd.elec5619.dao;

import au.edu.usyd.elec5619.domain.*;

public interface PostDao {
	
	public void createPost(Post post);

	public void incrementNumPosts(int eventId);
	
	public void incrementNumComments(int postId);
	
	public void incrementNumCommentLikes(int commentId);
	
	public void incrementNumPostLikes(int postId);
	
	public void saveCommentLike(int commentId, long userId);
	
	public void savePostLike(int postId, long userId);
	
	public void createComment(Comment comment);
	
	public Post getPostById(int id);
	
	public Comment getCommentById(int id);
	
	public boolean getUserLikedPost(int postId, long userId);
	
	public boolean getUserLikedComment(int commentId, long userId);
	
}
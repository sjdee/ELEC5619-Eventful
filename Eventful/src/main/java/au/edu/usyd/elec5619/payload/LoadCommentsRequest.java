package au.edu.usyd.elec5619.payload;

public class LoadCommentsRequest {

	int postId;
    int oldestCommentId;
    
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getOldestCommentId() {
		return oldestCommentId;
	}
	public void setOldestCommentId(int oldestCommentId) {
		this.oldestCommentId = oldestCommentId;
	}
	
}

package au.edu.usyd.elec5619.payload;

import java.util.List;

import au.edu.usyd.elec5619.domain.Comment;
import au.edu.usyd.elec5619.domain.Post;

public class LoadCommentResponse {

	List<Comment> comments;

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
}
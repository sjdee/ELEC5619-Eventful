package au.edu.usyd.elec5619.payload;

import java.util.List;

import au.edu.usyd.elec5619.domain.Post;

public class LoadPostResponse {

	List<Post> posts;

	String testString;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getTestString() {
		return testString;
	}

	public void setTestString(String testString) {
		this.testString = testString;
	}
	
}

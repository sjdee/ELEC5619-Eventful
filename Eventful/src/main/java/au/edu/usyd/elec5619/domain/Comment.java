package au.edu.usyd.elec5619.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@EnableAutoConfiguration
@Table(name = "Comment")
public class Comment implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "contents")
	private String contents;

	@Column(name = "timePosted")
	private Date timePosted;
	
	@Column(name="numLikes")
	private int numLikes;
	
	// Non column variable denoting whether current user has liked a post
	@Transient
	private Boolean isLiked;
	
	@JoinColumn(name = "commenter_email")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User commenter;

	@JoinColumn(name = "post_id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Post post;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedComments")
    private Set<User> likedUsers;
	
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public int getId() {
		return id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Set<User> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(Set<User> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTimePosted(Date date) {

		this.timePosted = date;
		
	}
	
	public Date getTimePosted() {
		
		return this.timePosted;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public Boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
	
}

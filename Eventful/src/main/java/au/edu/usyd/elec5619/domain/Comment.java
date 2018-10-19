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

	@Column(name = "timeposted")
	private Date timeposted;
	
	@Column(name="numlikes")
	private int numlikes;
	
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

	public void setTimeposted(Date date) {

		this.timeposted = date;
		
	}
	
	public Date getTimeposted() {
		
		return this.timeposted;
	}

	public int getNumlikes() {
		return numlikes;
	}

	public void setNumlikes(int numlikes) {
		this.numlikes = numlikes;
	}
	
}

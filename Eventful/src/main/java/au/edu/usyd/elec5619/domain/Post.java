package au.edu.usyd.elec5619.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Data
@Entity
@Table(name="Post")
public class Post implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="timeposted")
	private Date timeposted;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="post")
	private Set<Comment> comments;
	
	@JoinColumn(name = "event_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Event event;
	
	@JoinColumn(name = "poster_email")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private User poster;
	
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "likedPosts")
    private Set<User> likedUsers;
	
	@Column(name="numcomments")
	private int numcomments;
	
	@Column(name="numlikes")
	private int numlikes;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimeposted() {
		return timeposted;
	}

	public void setTimeposted(Date timePosted) {
		this.timeposted = timePosted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public int getNumcomments() {
		return numcomments;
	}

	public void setNumcomments(int numComments) {
		this.numcomments = numComments;
	}

	public int getNumlikes() {
		return numlikes;
	}

	public void setNumlikes(int numLikes) {
		this.numlikes = numLikes;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}
	
	public int getId() {
		return id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
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
	
	
	
}

package au.edu.usyd.elec5619.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;
import lombok.Data;

@Data
@Entity
@Table(name="Event")
public class Event implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="location")
	public String location;
	
	@Column(name="description")
	public String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="event")
	public List<Post> posts;

	@JoinColumn(name = "organiser_email")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public User organiser;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}
	
}

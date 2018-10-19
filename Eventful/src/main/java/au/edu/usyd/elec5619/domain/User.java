package au.edu.usyd.elec5619.domain;

import java.util.Set;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@Access(AccessType.FIELD)
@EnableAutoConfiguration
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "alias") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@NotEmpty
	@Column(name = "email", updatable = false)
	public String email;

	@NotEmpty
	@Column(name = "password")
	public String password;

	@NotEmpty
	@Column(name = "alias")
	public String alias;

	@Column(name = "avatar")
	private byte[] avatar;

	@Column(name = "bio")
	private String bio;

	@Column(name = "avgEventRating", columnDefinition = "double default 0.0")
	private double avgEventRating;

	private boolean enabled = true;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commenter")
	private Set<Comment> comments;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organiser")
	private Set<Event> events;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "poster")
	private Set<Post> posts;

	@JoinTable(name = "likedPosts")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Post> likedPosts;

	@JoinTable(name = "likedComments")
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Comment> likedComments;

	// Getters
	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public String getAlias() {
		return this.alias;
	}

	public String getPassword() {
		return this.password;
	}

	public String getBio() {
		return this.bio;
	}

	public double getAvgEventRating() {
		return this.avgEventRating;
	}

	public boolean getEnabled() {
		return this.enabled;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public Set<Post> getLikedPosts() {
		return this.likedPosts;
	}

	public Set<Comment> getLikedComments() {
		return this.likedComments;
	}

	// Setters
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setAvgEventRating(double avgEventRating) {
		this.avgEventRating = avgEventRating;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = false;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setLikedPosts(Set<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public void setLikedComments(Set<Comment> likedComments) {
		this.likedComments = likedComments;
	}

}

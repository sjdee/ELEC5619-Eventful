package au.edu.usyd.elec5619.domain;

import java.util.Set;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import au.edu.usyd.elec5619.domain.constraint.PasswordsEqualConstraint;

@Data
@Entity
@Access(AccessType.FIELD)
@EnableAutoConfiguration
@Table(name = "User")
@PasswordsEqualConstraint(message = "Passwords do not match.")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable=false, nullable=false)
	private long id;

	@NotEmpty(message = "Please provide an email")
	@Email(message = "Please provide a valid email")
	@Column(name = "email", updatable=false, nullable=false, unique=true)
	public String email;

	@NotEmpty(message = "Please provide a password")
	@Column(name = "password", nullable=false)
	public String password;
	
	@Transient
	public String confirmPassword;

	@NotEmpty(message = "Please provide an alias")
	@Column(name = "alias", nullable=false)
	public String alias;

	@Column(name = "filePath", unique=true)
	private String filePath;

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

	@ManyToMany(mappedBy = "subscribedUsers", cascade = CascadeType.MERGE,  fetch = FetchType.EAGER)
	private Set<Event> subscribedEvents;
	
	@OneToMany(mappedBy = "user")
	private Set<Rating> ratings;
	
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
	
	public String getConfirmPassword() {
		return this.confirmPassword;
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

	public Set<Event> getSubscribedEvents() {
		return this.subscribedEvents;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	
    public Set<Rating> getRatings() {
        return ratings;
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
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
	public void setSubscribedEvents(Set<Event> subscribedEvents) {
		this.subscribedEvents = subscribedEvents;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}

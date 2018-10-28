package au.edu.usyd.elec5619.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import au.edu.usyd.elec5619.domain.Post;
import au.edu.usyd.elec5619.domain.User;
import lombok.Data;

@Data
@Entity
@EnableAutoConfiguration
@Table(name="Event")
public class Event implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int id;
	
	@Column(name="title")
	public String title;
	
	@Column(name="location")
	public String location;
	
	@Column(name="datetime")
	public Date datetime;
	
	@Column(name="maxPeople")
	public int maxPeople;
	
	@Column(name="description")
	public String description;
	
	@Column(name="repetition")
	// 0 = One off; 1 = Daily; 3 = Weekly; 2= Fortnightly; 4 = Monthly	
	public int repetition;
	
	@Column(name="eventImagePath")
	public String eventImagePath;
	
	@Column(name="numPosts")
	public int numPosts;
	
	// 0 = false; 1 = true
	@Column(name="cancelled")
	public boolean cancelled;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="event")
	public List<Post> posts;

	@JoinColumn(name = "organiser")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public User organiser;
	
	
    @ManyToMany(cascade={CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "subscribedEvents", 
        joinColumns = { @JoinColumn(name = "event_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    public Set<User> subscribedUsers;
	
    @OneToMany(mappedBy = "event")
    private Set<Rating> ratings;

    //    methods
	public int getId() {
		return id;
	}
	
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

	public Date getDatetime() {
		return this.datetime;
	}
	
	public void setDatetime(Date eventTime) {
		this.datetime = eventTime;
	}
	
	public int getMaxPeople(){
		return this.maxPeople;
	}
	
	public void setMaxPeople(int maxPeople){
		this.maxPeople = maxPeople;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRepetition(){
		return this.repetition;
	}
	
	public void setRepetition(int repetition){
		this.repetition = repetition;
	}
	
	public String getEventImagePath(){
		return this.eventImagePath;
	}
	
	public void setEventImagePath(String eventImagePath){
		this.eventImagePath = eventImagePath;
	}
	
	public boolean getCancelled(){
		return this.cancelled;
	}
	
	public void setCancelled(boolean cancelled){
		this.cancelled =cancelled;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}	
	
	public User getOrganiser() {
		return this.organiser;
	}
	
	public void setOrganiser(User organiser) {
		this.organiser = organiser;
	}	
	
	public Set<User> getSubscribedUsers() {
		return this.subscribedUsers;
	}
	
	public void setSubscribedUsers(Set<User> subscribedUsers) {
		this.subscribedUsers = subscribedUsers;
	}
	
    public Set<Rating> getRatings() {
        return ratings;
    }
 
    public void setUserGroups(Set<Rating> ratings) {
        this.ratings = ratings;
    }
}

package au.edu.usyd.elec5619.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import au.edu.usyd.elec5619.domain.Event;
import au.edu.usyd.elec5619.domain.User;
import lombok.Data;

@Data
@Entity
@EnableAutoConfiguration
@Table(name="Rating", 
	   uniqueConstraints = @UniqueConstraint(columnNames={"user_id", "event_id"}))
public class Rating implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="ratingValue")
	private double ratingValue;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
	private Event event;
	
    public int getId() {
		return id;
	}
	 
	public void setId(int id) {
	    this.id = id;
	}
	

	public User getUser() {
		return this.user;
	}
	
    public void setUser(User user) {
        this.user = user;
    }
    
    public Event getEvent() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    public double getRatingValue() {
    	return ratingValue;
    }
 
    public void setRatingValue(double ratingValue) {
    	this.ratingValue = ratingValue;
    }
}

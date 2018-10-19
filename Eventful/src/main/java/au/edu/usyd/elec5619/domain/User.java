package au.edu.usyd.elec5619.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Access(AccessType.FIELD)
@EnableAutoConfiguration
@Table(name = "User", uniqueConstraints = { @UniqueConstraint(name = "email_user_uc", columnNames = "email"),
		@UniqueConstraint(name = "alias_user_uc", columnNames = "alias") })
public class User implements UserDetails {

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

	@Column(name = "avgeventrating", columnDefinition = "double default 0.0")
	private double avgeventrating;

	private boolean enabled = true;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "commenter")
	private Set<Comment> comments;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "organiser")
	private Set<Event> events;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "poster")
	private Set<Post> posts;
	
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
	
	public double getAvgeventrating() {
		return this.avgeventrating;
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
	
	public void setAvgrating(double avgeventrating) {
		this.avgeventrating = avgeventrating;
	}
	
	// Override UserDetail interface
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
